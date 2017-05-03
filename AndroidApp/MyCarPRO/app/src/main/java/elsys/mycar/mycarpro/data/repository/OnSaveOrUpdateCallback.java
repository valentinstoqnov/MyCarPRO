package elsys.mycar.mycarpro.data.repository;

public interface OnSaveOrUpdateCallback<T> {

    void onSuccess(T item);

    void onFailure();
}
