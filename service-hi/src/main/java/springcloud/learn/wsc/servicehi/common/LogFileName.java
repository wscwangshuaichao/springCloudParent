package springcloud.learn.wsc.servicehi.common;

import org.apache.commons.lang.StringUtils;

public enum LogFileName {

    SYNCGAT1("SYNCGAT1"),
    SYNCGAT2("SYNCGAT2");

    private String logFileName;

    LogFileName(String fileName) {
        this.logFileName = fileName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public static LogFileName getAwardTypeEnum(String value) {
        LogFileName[] arr = values();
        for (LogFileName item : arr) {
            if (null != item && StringUtils.isNotBlank(item.logFileName)) {
                return item;
            }
        }
        return null;
    }

}
