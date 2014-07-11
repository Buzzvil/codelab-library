package com.hovans.android.concurrent;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

@SuppressWarnings("UnusedDeclaration")
abstract class Usage {

    public void useCase00() {
        // ThreadGuest 의 사용 예시
        ThreadGuest.ChainBlocker blocker = new ThreadGuest.ChainBlocker();
        blocker.block();
        new ThreadGuest() {
            public Object run(long waitTimeMillis) {
                setObject(somethingGreat());
                return null;
            }
        }.addChain(blocker, new ThreadGuest() {
            public Object run(long waitTimeMillis) {
                somethingAwesome();
                return null;
            }
        }).addChain(200, new ThreadGuest() {
            public Object run(long waitTimeMillis) {
                somethingPerfect(getObject());
                return null;
            }
        }).execute();
        somethingOther();
        blocker.unblock();
    }

    public void useCase01() {
        new AsyncTask<Integer, Integer, Integer>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Integer... params) {
                return null;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
            }

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            protected void onCancelled(Integer integer) {
                super.onCancelled(integer);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        };

        // ThreadGuest 의 사용 예시
        ThreadGuest.ChainBlocker blocker = new ThreadGuest.ChainBlocker();
        blocker.block();
        new ThreadGuest() {
            @Override
            public void offerFail() {
                super.offerFail();
            }

            public Object run(long waitTimeMillis) {
                setObject(somethingGreat());
                return null;
            }

            @Override
            public void after(Object result) {
                super.after(result);
            }
        }.addChain(blocker, new ThreadGuest() {
            public Object run(long waitTimeMillis) {
                somethingAwesome();
                return null;
            }
        }).execute();
        blocker.unblock();
    }

    public void useCase02() {
        // ThreadGuest 에 제네릭을 사용할 경우 어떻게 되는지 체크
        ThreadGeneral<Integer, Boolean> test = new ThreadGeneral<Integer, Boolean>() {

            @Override
            public Boolean run(long waitTimeMillis, Integer integer) {
                return null;
            }

            @Override
            public void afterResult(Boolean aBoolean) {
                super.afterResult(aBoolean);
            }
        };
        // 체이닝은 체인된 객체(tail of chain)에게 이루어져야 한다.
        test.addChain(111, new ThreadGeneral<Boolean, Object>() {

            @Override
            public Object run(long waitTimeMillis, Boolean aBoolean) {
                return null;
            }
        });
        // 실행은 첫 객체(head of chain)에 대해 이루어져야 한다.
        test.execute(1111);
        // 기존의 사용성은 체이닝의 결과로 head 만 리턴해야 구현 가능하다.
        // 이로부터 제네릭을 적용하면 사용성이 깨지는 것을 볼 수 있다.
    }

    abstract void somethingOther();

    abstract Object somethingGreat();

    abstract void somethingAwesome();

    abstract void somethingPerfect(Object obj);
}
