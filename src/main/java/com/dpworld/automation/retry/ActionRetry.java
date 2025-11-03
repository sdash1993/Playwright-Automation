package com.dpworld.automation.retry;

import com.dpworld.automation.exceptions.ActionFailedException;
import com.dpworld.automation.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;

public class ActionRetry extends RetryUtil {

    private final Action action;
    private int pollingTime;

    public ActionRetry(final Action action) {
        this.action = action;
    }

    public ActionRetry ignoringError() {
        this.ignoreError = true;
        return this;
    }

    public ActionRetry withRetryCount(final int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public ActionRetry withMessage(final String message) {
        this.failureMessage = message;
        return this;
    }

    public ActionRetry pollingEvery(final int milliSeconds) {
        this.pollingTime = milliSeconds;
        return this;
    }

    public ActionRetry withBackOffAction(final Action backOffAction) {
        this.backOffAction = backOffAction;
        return this;
    }

    public void perform() {
        Throwable lastException = null;
        for (int i = 0; i < retryCount; i++) {
            try {
                action.perform();
                return;
            } catch (Throwable t) {
                lastException = t;
                LogUtils.info(String.format("Retrying action Again due to error %s", t.getMessage()));
                backOff();
            }
        }
        if (StringUtils.isEmpty(failureMessage)) {
            throw new ActionFailedException(String.format("Action failed after retrying %s times",
                    retryCount), lastException);
        }
        throw new ActionFailedException(failureMessage);
    }
}
