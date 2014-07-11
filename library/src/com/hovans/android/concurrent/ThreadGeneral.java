package com.hovans.android.concurrent;

/**
 * 제네릭 구현시 사용성 테스트. use-case 를 보기 위해 만들어본다.
 * @param <Argument>
 * @param <Result>
 */
abstract class ThreadGeneral<Argument, Result> extends ThreadGuest {

    private Argument mArgument;

    public void execute(Argument argument) {
        mArgument = argument;
        super.execute();
    }

    @Override
    public final Result run(long waitTimeMillis) {
        return run(waitTimeMillis, mArgument);
    }

    public abstract Result run(long waitTimeMillis, Argument argument);

    @SuppressWarnings("unchecked")
    @Override
    public final void after(Object result) {
        afterResult((Result) result);
    }

    @SuppressWarnings("unused")
    public void afterResult(Result result) {
        // do nothing
    }

    @Override
    public ThreadGuest addChain(long delayMillis, ThreadGuest nextGuest) {
        return super.addChain(delayMillis, nextGuest);
    }

    @SuppressWarnings({"unused", "unchecked"})
    public <Unknown> ThreadGeneral<Result, Unknown> addChain(long delayMillis, ThreadGeneral<Result, Unknown> nextGuest) {
        return (ThreadGeneral<Result, Unknown>) super.addChain(delayMillis, nextGuest);
    }
}
