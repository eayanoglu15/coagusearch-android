package com.example.coagusearch.network

import com.example.coagusearch.typing.Result
import io.reactivex.Maybe
import io.reactivex.Single

typealias ApiResult<T> = Single<out Result<T, BaseError>>
typealias ApiMaybe<T> = Maybe<out Result<T, BaseError>>
typealias ApiResultList<T> = Single<out Result<T, List<BaseError>>>
