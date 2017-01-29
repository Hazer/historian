package net.yslibrary.historian.internal;

import net.yslibrary.historian.Historian;
import net.yslibrary.historian.LogEntity;

/**
 * Created by yshrsmz on 2017/01/21.
 */

public class LogWritingTask implements Runnable {

  private final Historian.Callbacks callbacks;
  private final LogWriter logWriter;
  private final LogEntity log;

  public LogWritingTask(Historian.Callbacks callbacks,
                        LogWriter logWriter,
                        LogEntity log) {
    this.callbacks = callbacks;
    this.logWriter = logWriter;
    this.log = log;
  }

  @Override
  public void run() {

    try {
      logWriter.log(log);

      callbacks.onSuccess();
    } catch (final Throwable throwable) {
      callbacks.onFailure(throwable);
    }
  }
}
