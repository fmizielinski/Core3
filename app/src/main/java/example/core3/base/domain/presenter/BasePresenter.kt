package example.core3.base.domain.presenter

import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import example.core3.base.domain.viewing.BaseViewing
import example.core3.base.domain.viewing.Viewing
import io.reactivex.disposables.CompositeDisposable
import org.koin.standalone.KoinComponent
import java.util.*

abstract class BasePresenter<T : BaseViewing> : ViewModel(), LifecycleObserver, KoinComponent {

    private var view: T? = null

    private var isFirstBind = true

    private val latestViewChanges: Queue<Viewing<T>> = LinkedList()

    protected val compositeDisposable = CompositeDisposable()

    //region lifecycle

    abstract fun onFirstBind()

    abstract fun onViewRestoreState()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    //endregion lifecycle

    //region binding

    @CallSuper
    open fun bind(view: T) {
        if (this.view != null)
            throw RuntimeException("Concurrent view bind! Unexpected, second instance of view occurred before first one unbind!")

        this.view = view
        if (isFirstBind) {
            isFirstBind = false
            onFirstBind()
        } else
            onViewRestoreState()
        while (!latestViewChanges.isEmpty()) {
            present(latestViewChanges.poll())
        }
    }

    @CallSuper
    open fun unbind() {
        view = null
    }

    //endregion binding

    //region presentation

    protected fun present(viewing: Viewing<T>) {
        if (view != null)
            viewing.invoke(view!!)
        else
            latestViewChanges.add(viewing)
    }

    //endregion presentation
}