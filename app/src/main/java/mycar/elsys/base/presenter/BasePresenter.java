package mycar.elsys.base.presenter;


public abstract class BasePresenter<V> {

    protected V mView;

    public void attachView(V mView) {
        this.mView = mView;
    }

    public void detachView() {
        mView = null;
    }

    protected boolean isViewAttached() {
        return mView != null;
    }
}
