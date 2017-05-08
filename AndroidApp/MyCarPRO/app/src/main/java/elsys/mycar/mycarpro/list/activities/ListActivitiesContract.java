package elsys.mycar.mycarpro.list.activities;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface ListActivitiesContract {

    interface View<P, E> extends BaseView<P> {

        void showDetailItemUi(String itemId);

        void showNoItemsFound();

        void showItems(List<E> items);

        void showProgress();

        void hideProgress();

        void showMessage(String message);

        boolean isActive();
    }

    interface Presenter<T> extends BasePresenter{

        void openItemDetails(T item);

        void swapDataSet(List<T> items);

        boolean isDataMissing();
    }
}
