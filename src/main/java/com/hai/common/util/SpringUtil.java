package com.hai.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Calendar;
import java.util.Date;

public class SpringUtil  {

    private static ApplicationContext applicationContext = null;
// 非@import显式注入，@Component是必须的，且该类必须与main同包或子包
    // 若非同包或子包，则需手动import 注入，有没有@Component都一样
    // 可复制到Test同包测试

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext  = applicationContext;
        }
       
    }

//    public static void main(String[] args) {
//        int dd = RandomUtil.getRandomOne(500,1000);
//        System.out.println(dd);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        System.out.println(calendar.get(Calendar.HOUR_OF_DAY ));
//        System.out.println(getNumbers(new Date()));
//
//    }

    public static int getNumbers(Date thisDate){
        int dd = RandomUtil.getRandomOne(0,500);
        Calendar calendar = Calendar.getInstance();
        String stringDate = DateUtils.formatDate(calendar.getTime(),DateUtils.FORMAT_YYYY_MM_DD);
        int k1 = 0;
        int k2 = 0;
        int k3 = 0;
        int k4 = 0;
        int k5 = 0;
        if(stringDate.equals("2019-08-17") || stringDate.equals("2019-08-20") || stringDate.equals("2019-08-22") || stringDate.equals("2019-08-24") || stringDate.equals("2019-08-27")
        || stringDate.equals("2019-08-29") || stringDate.equals("2019-08-31") || stringDate.equals("2019-09-03") || stringDate.equals("2019-09-05") || stringDate.equals("2019-09-07")){
            k1 = 1000;
            k2 = 3000;
            k3 = 6000;
            k4 = 8000;
            k5 = 10000;
        }


        if(calendar.get(Calendar.HOUR_OF_DAY ) == 0){
            return 4000 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 1){
            return 3500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 2){
            return 2500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 3){
            return 1500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 4){
            return 1000 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 5){
            return 1000 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 6){
            return 2000 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 7){
            return 2000 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 8){
            return 2500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 9){
            return k1+3000 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 10){
            return k1+3500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 11){
            return k2+4500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 12){
            return k2+4500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 13){
            return k2+4500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 14){
            return k2+4500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 15){
            return k2+4500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 16){
            return k2+4500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 17){
            return k2+4500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 18){
            return k2+4500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 19){
            return k3+5500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 20){
            return k5+7500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 21){
            return k5+8500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 22){
            return k4+6500 + dd;
        }else if(calendar.get(Calendar.HOUR_OF_DAY ) == 23){
            return k2+5000 + dd;
        }
        return 155872;
    }




    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);

    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
