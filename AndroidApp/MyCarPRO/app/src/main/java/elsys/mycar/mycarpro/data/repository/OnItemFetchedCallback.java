package elsys.mycar.mycarpro.data.repository;

public interface OnItemFetchedCallback<T> {

    void onSuccess(T item);

    void onFailure();
}
