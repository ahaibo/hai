/**
 *
 */
package com.hai.javase.thread.sync.lock;

import com.hai.javase.thread.sync.User;

/**
 * @author Administrator
 */
public class UserLockRunnable implements Runnable {
    private User user;

    public UserLockRunnable() {
    }

    /**
     * @param user
     */
    public UserLockRunnable(User user) {
        this.user = user;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        this.user.transferAccount();
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

}
