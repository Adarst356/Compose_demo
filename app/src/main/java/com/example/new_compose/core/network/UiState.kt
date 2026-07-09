 package com.example.new_compose.core.network
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed class UiState<out T> {
    data object None : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
fun <T> UiState<T>.isNone() =  this is UiState.None
fun <T> UiState<T>.isRetry() = this is UiState.Error || this is UiState.None
fun <T> UiState<T>.isLoading() = this is UiState.Loading
fun <T> UiState<T>.isError() = this is UiState.Error
fun <T> UiState<T>.isSuccess() = this is UiState.Success
fun <T> UiState<T>.getErrorOrNull() = if (this is UiState.Error) this.message else null
fun <T> UiState<T>.getDataOrNull() = if (this is UiState.Success) this.data else null

@OptIn(ExperimentalContracts::class)
inline fun <T> UiState<T>.onSuccess(action: (data: T) -> Unit): UiState<T> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is UiState.Success) {
        action(data)
    }
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T> UiState<T>.onError(action: (message: String?) -> Unit): UiState<T> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is UiState.Error) {
        action(message)
    }
    return this
}
@OptIn(ExperimentalContracts::class)
inline fun <T> UiState<T>.onLoading(action: () -> Unit): UiState<T> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is UiState.Loading) {
        action()
    }
    return this
}