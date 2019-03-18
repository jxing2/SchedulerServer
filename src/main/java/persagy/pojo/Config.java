package persagy.pojo;
/*
 * Author: Jxing
 * Create Time: 2019/3/12
 */

public class Config {
    private String ip;
    private int port;
    private String mysql;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMysql() {
        return mysql;
    }

    public void setMysql(String mysql) {
        this.mysql = mysql;
    }
}
