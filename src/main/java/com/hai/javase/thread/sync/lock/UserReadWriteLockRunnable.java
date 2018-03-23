/**
 *
 */
package com.hai.javase.thread.sync.lock;

import com.hai.javase.thread.sync.MyCount;
import com.hai.javase.thread.sync.User;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author Administrator
 */
public class UserReadWriteLockRunnable implements Runnable {
    private User user;
    private ReadWriteLock readWriteLock;
    private boolean isCheck;

    public UserReadWriteLockRunnable() {
    }

    /**
     * @param user
     * @param readWriteLock
     */
    public UserReadWriteLockRunnable(User user, ReadWriteLock readWriteLock) {
        this.user = user;
        this.readWriteLock = readWriteLock;
    }

    /**
     * @param user
     * @param readWriteLock
     * @param isCheck
     */
    public UserReadWriteLockRunnable(User user, ReadWriteLock readWriteLock, boolean isCheck) {
        this.user = user;
        this.readWriteLock = readWriteLock;
        this.isCheck = isCheck;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        if (this.isCheck) {
            // 获取读锁
            this.readWriteLock.readLock().lock();

            // 模拟业务：查询
            System.out.println("读：" + this.user.getCode() + "正在查询" + this.user.getMyCount()
                    + "账户，当前金额为" + this.user.getMyCount().getRemainingSum() + "\n");

            // 释放读锁
            this.readWriteLock.readLock().unlock();
        } else {
            // 获取写锁
            this.readWriteLock.writeLock().lock();

            // 模拟现金业务
            MyCount myCount = this.user.getMyCount();
            System.out.println("写：" + this.user.getCode() + "正在操作" + myCount + "账户，金额为" + this.user.getCash()
                    + "，当前金额为" + myCount.getRemainingSum());

            myCount.setRemainingSum(myCount.getRemainingSum() + this.user.getCash());
            System.out.println("写：" + this.user.getCode() + "操作" + myCount + "账户成功，金额为" + this.user.getCash()
                    + "，当前金额为" + myCount.getRemainingSum() + "\n");

            // 释放写锁
            this.readWriteLock.writeLock().unlock();
        }
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the readWriteLock
     */
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    /**
     * @param readWriteLock the readWriteLock to set
     */
    public void setReadWriteLock(ReadWriteLock readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    /**
     * @return the isCheck
     */
    public boolean isCheck() {
        return isCheck;
    }

    /**
     * @param isCheck the isCheck to set
     */
    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

}
