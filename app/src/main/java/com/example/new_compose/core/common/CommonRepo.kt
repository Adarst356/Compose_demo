package com.example.new_compose.core.common

data class CommonRes(
    val msg: String? = null,
    val statuscode: Int? = null,
    val sdkType: Int? = null,
    val oid: Int? = null,
    val circleID: Int? = null,
    val commRate: Double? = null,
/*    val companyProfile: CompanyProfile? = null,
    val userInfo: UserDetailInfo? = null,
    val userDox: List<KycDoc>? = null,
    val pDetail: List<PackageDetails>? = null,
    val bankMasters: List<MasterBankItem>? = null,
    val banks: List<FundRequestBank>? = null,
    val slabDetailDisplayLvl: List<DisplayCommission>? = null,
    val slabRangeDetail: List<SlabRangeDetail>? = null,
    val banners: List<Banner>? = null,
    val notifications: List<NotificationData>? = null,
    val userListRoleWise: List<UserRoleList>? = null,
    val fundRequestToUsers: List<FundRequestUser>? = null,

    val transactionDetail: DmtReceiptData? = null,
    val pGModelForApp: PGModelForApp? = null,
    val newsContent: News? = null,
    val pGs: List<PgData>? = null,*/

    val isOTPRequired: Boolean? = null,
    val is2FactorRequired: Boolean? = null,
    val referenceID: Int? = null,
    val refID: String? = null,
    val chargedAmount: Double? = null,
    val balance: Double? = null,
)

    open class BaseResponse<T>(
        val data: T? = null,
        val isVersionValid: Boolean? = null,
        val isAppValid: Boolean? = null,
        val isPasswordExpired: Boolean? = null,
        msg: String? = null,
        statuscode: Int? = null
    ) : Base(msg, statuscode)

    open class Base(
        val msg: String? = null,
        val statuscode: Int? = null
    )

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val statusCode: Int,
    val data: T?
)

