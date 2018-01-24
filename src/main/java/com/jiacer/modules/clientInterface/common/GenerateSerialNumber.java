package com.jiacer.modules.clientInterface.common;

import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.conts.SequeConst;
import com.jiacer.modules.mybatis.dao.SequeGenerateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * 序列号生成器
 * Created by gaoyan on 06/07/2017.
 */
@Service
public class GenerateSerialNumber implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger("generateLogger");

    private static ConcurrentHashMap<SequeConst,ArrayBlockingQueue<String>> storage = null;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private static NumberFormat nf = NumberFormat.getInstance();

    static {
        nf.setGroupingUsed(false);
        nf.setMaximumIntegerDigits(Integer.SIZE);
        nf.setMinimumIntegerDigits(5);
    }


    private static int storageSize = 0;
    private static int incerment = 0;

    @Autowired
    private SequeGenerateMapper generate;

    /**
     * 获取一个序列号[10][YYYYMMDD][00000]
     *
     * @return
     */
    public String take(SequeConst seq) {
        //获取序列号，堵塞模式
        try {
            return storage.get(seq).take();

        } catch (InterruptedException e) {
            log.error("generate order no failure : " + e.getMessage());
            return null;
        }
    }

    /**
     * 生成ORDER_NO并加入队列
     *
     * @return
     */
    public void generate(final SequeConst seq) throws InterruptedException {
        if(storage.get(seq) == null){
            storage.put(seq, new ArrayBlockingQueue<String>(storageSize));
        }
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String date = format.format(new Date());
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("date", date);
                    param.put("num", 0);
                    param.put("type", seq.getType());
                    //1. 查询数据库值
                    Integer currentNum = generate.getMaxSerialNum(param);
                    if (currentNum == null) {
                        generate.initGenerator(param);
                        currentNum = 0;
                    }
                    param.put("num", currentNum + incerment);
                    //2. update value += 10
                    generate.generateSN(param);
                    log.info("generate "+seq.getType()+" storage ============: " + (currentNum + 1) + "-" + (currentNum + 11) + "=================");
                    //System.out.println("generate storage ============: " + (currentNum + 1) + "-" + (currentNum + 11) + "===============");
                    //3. 生成10个序列号放入storage, 堵塞模式
                    int i = 0;
                    while (i < incerment) {
                        i++;
                        currentNum++;
                        StringBuffer serialNo = new StringBuffer(Constants.ENV.toUpperCase().charAt(0)+"");
                        serialNo.append(seq.getCode());
                        serialNo.append(date);
                        serialNo.append(nf.format(currentNum));
                        try {
                            log.info("add "+seq.getType()+" storage ============: " + serialNo.toString());
                            //System.out.println("add storage ============: " + serialNo.toString());
                            storage.get(seq).put(serialNo.toString());
                        } catch (InterruptedException e) {
                            log.error(seq.getType() + "storage put error : " + e.getMessage());
                        }
                    }
                }
            }
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties prop = new Properties();
        InputStream in = SwiftpassConfig.class.getResourceAsStream("/env.properties");
        try {
            prop.load(in);
            storageSize = Integer.valueOf(prop.getProperty("storage_size").trim());
            incerment = Integer.valueOf(prop.getProperty("incerment").trim());
            storage = new ConcurrentHashMap<SequeConst,ArrayBlockingQueue<String>>();
            //常驻执行线程
            generate(SequeConst.ORDER);
            generate(SequeConst.PAY_INFO);
            generate(SequeConst.FINANCE_FLOW);
            generate(SequeConst.REDPACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
