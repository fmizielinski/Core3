package example.core3.common

import io.reactivex.Observable
import io.reactivex.Single

fun <T> Single<T>.runAsyncReturnOnMain(): Single<T> =
	this.subscribeOn(io.reactivex.schedulers.Schedulers.io())
		.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())

fun <T> Observable<T>.runAsyncReturnOnMain(): Observable<T> =
	this.subscribeOn(io.reactivex.schedulers.Schedulers.io())
		.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())