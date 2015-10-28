package boot;

import java.util.concurrent.*;

/**
 * Singleton thread pool - accesible from entire code
 * in charge of adding new callables/runnables to Fixed thread pool.
 *
 * @author Artiom,Yoav
 */

public class GlobalThreadPool
{
    private ExecutorService executor= Executors.newFixedThreadPool(10);
    private static GlobalThreadPool ourInstance = new GlobalThreadPool();

    public void setAndCreateNumOfThreads(Integer numOfThreads)
    {

    }

    public static GlobalThreadPool getInstance()
    {
        return ourInstance;
    }

    public <T> Future <T> addCallableToPool(Callable<T> callable)
    {
        return executor.submit(callable);
    }

    public Future<?> addRunnableToPool(Runnable runnable) throws ExecutionException, InterruptedException
    {
        return executor.submit(runnable);
    }
}
