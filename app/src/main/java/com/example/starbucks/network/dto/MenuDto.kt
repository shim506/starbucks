package com.example.starbucks.network.dto

data class MenuDto(
    val list: List<MenuItemsDto>
)

data class MenuItemsDto(
    val all_CATE_CD: String,
    val allergy: String,
    val app_IMAGE: String,
    val caffeine: String,
    val caffeine_L: String,
    val card_MONTH: String,
    val card_TABLE: String,
    val card_YEAR: String,
    val cate_CD: String,
    val cate_NAME: String,
    val cate_TABLE: String,
    val cate_TYPE: String,
    val chabo: String,
    val chabo_L: String,
    val cholesterol: String,
    val cholesterol_L: String,
    val content: String,
    val del_YN: String,
    val egift_CARD_YN: String,
    val f_CATE_CD: String,
    val fat: String,
    val fat_L: String,
    val file_MASTER: String,
    val file_NAME: String,
    val file_PATH: String,
    val file_TABLE: String,
    val first_INDEX: Int,
    val front_VIEW_YN: String,
    val gift_VALUE: String,
    val hot_YN: String,
    val img_ORDER: String,
    val img_UPLOAD_PATH: String,
    val info_TABLE: String,
    val kcal: String,
    val kcal_L: String,
    val last_INDEX: Int,
    val mod_DT: String,
    val mod_USER: String,
    val msr_SEQ: Int,
    val msr_SEQ2: String,
    val new_EDATE: String,
    val new_SDATE: String,
    val newicon: String,
    val note_TYPE: String,
    val nut_TABLE: String,
    val page_INDEX: Int,
    val page_SIZE: Int,
    val page_UNIT: Int,
    val pair_TABLE: String,
    val pcate_CD: String,
    val premier: String,
    val price: String,
    val pro_SEQ: Int,
    val product_CD: String,
    val product_ENGNM: String,
    val product_NM: String,
    val protein: String,
    val protein_L: String,
    val recomm: String,
    val recommend: String,
    val record_COUNT_PER_PAGE: Int,
    val reg_DT: String,
    val reg_USER: String,
    val rnum: Int,
    val sat_FAT: String,
    val sat_FAT_L: String,
    val search_1_CATE: Any,
    val search_2_CATE: Any,
    val search_3_CATE: Any,
    val search_DATE_TYPE: Any,
    val search_END_DATE: Any,
    val search_KEY: String,
    val search_SALE_TYPE: Any,
    val search_START_DATE: Any,
    val search_VALUE: Any,
    val search_VIEW_YN: Any,
    val sell_CAT: String,
    val sodium: String,
    val sodium_L: String,
    val sold_OUT: String,
    val standard: String,
    val sub_VIEW: String,
    val sugars: String,
    val sugars_L: String,
    val theme_SEARCH: String,
    val thumbnail: String,
    val total_CNT: Int,
    val trans_FAT: String,
    val trans_FAT_L: String,
    val unit: String,
    val view_EDATE: String,
    val view_SDATE: String,
    val view_YN: String,
    val web_CARD_BIRTH: String,
    val web_IMAGE_MOBVIEW: String,
    val web_IMAGE_TABVIEW: String,
    val web_IMAGE_WEBVIEW: String,
    val web_NEW: String,
    val youtube_CODE: String
)