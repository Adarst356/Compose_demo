package com.example.new_compose.modules.dashboard.emi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.new_compose.core.network.UiState
import com.example.new_compose.modules.dashboard.emi.data.model.EmiResponse
import com.example.new_compose.modules.dashboard.emi.data.repo.EmiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmiViewModel @Inject constructor(
    private val repo: EmiRepo
) : ViewModel() {

    private val _emiState = MutableStateFlow<UiState<List<EmiResponse>>>(UiState.None)
    val emiState: StateFlow<UiState<List<EmiResponse>>> = _emiState.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    private var allCustomers: List<EmiResponse> = emptyList()

    init {
        getEmiCustomers()
    }
    fun getEmiCustomers() {
        viewModelScope.launch {
            repo.getPhoto {
                _emiState.value = it
                if (it is UiState.Success) {
                    allCustomers = it.data
                }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        if (allCustomers.isNotEmpty()) {
            val filteredList = if (query.isEmpty()) {
                allCustomers
            } else {
                allCustomers.filter {
                    it.title.contains(query, ignoreCase = true) ||
                            it.id.toString().contains(query)
                }
            }
            _emiState.value = UiState.Success(filteredList)
        }
    }
}
