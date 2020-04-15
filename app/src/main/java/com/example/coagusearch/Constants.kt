package com.example.coagusearch

import android.Manifest
import android.hardware.Sensor

/**
 * Project wide Constants
 */
//TODO: Delete unnecessary ones
object Constants {
    /**
     * Project wide Log.* Tag
     */

    const val TAG = "HospitalOnMobile"

    const val POPUP_QUESTION_DATA = "popup_questions"

    const val REQUEST_CAMERA_PERMISSION = 1
    const val REQUEST_READ_EXTERNAL_STORAGE_PERMISSION = 2
    const val REQUEST_VIDEO_PERMISSIONS = 3
    const val CAMERA_PERMISSIONS = Manifest.permission.CAMERA
    const val READ_EXTERNAL_PERMISSIONS = Manifest.permission.READ_EXTERNAL_STORAGE
    val VIDEO_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

    const val VOICE_OVER_QUESTION = "voiceOverQuestion.mp3"
    const val MEDICATION_IMAGE_NAME = "medicationImage.jpg"

    object Notification {
        const val ID = "migraine"
        const val CHANNEL_ID = "migraine_channel"
        const val ON_GOING_CHANNEL_ID = "migraine_foreground"
        const val UPLOAD_PROGRESS_CHANNEL_ID = "upload_progress_channel"
    }

    const val BACKGROUND_SERVICE_NOTIFICATION_ID = 9999
    const val AUTH_SERVICE_NOTIFICATION_ID = 9998
    const val UPLOAD_VIDEO_NOTIFICATION_ID = 9997

    const val ERROR_LOG_DATA = "ErrorLogData"

    const val FILE_TYPE_VERSION = 1

    enum class FileType(val type: String) {
        PHOTO("android_photo_${FILE_TYPE_VERSION}_${BuildConfig.VERSION_NAME}"),
        VIDEO("android_video_${FILE_TYPE_VERSION}_${BuildConfig.VERSION_NAME}"),
        AUDIO("android_audio_${FILE_TYPE_VERSION}_${BuildConfig.VERSION_NAME}"),
        SENSOR("android_sensor_${FILE_TYPE_VERSION}_${BuildConfig.VERSION_NAME}"),
        LOCATION("android_location_${FILE_TYPE_VERSION}_${BuildConfig.VERSION_NAME}"),
        CALENDAR("android_calendar_${FILE_TYPE_VERSION}_${BuildConfig.VERSION_NAME}"),
        AUDIO_INFO("android_audio_info_${FILE_TYPE_VERSION}_${BuildConfig.VERSION_NAME}"),
        NETWORK_INFO("android_network_info_${FILE_TYPE_VERSION}_${BuildConfig.VERSION_NAME}"),
        GOOGLE_FIT("android_google_fit_${FILE_TYPE_VERSION}_${BuildConfig.VERSION_NAME}"),
    }

    enum class Sex(val value: String) {
        FEMALE("female"),
        MALE("male")
    }

    object TokenType {
        const val FIREBASE_TOKEN = "FIREBASE_TOKEN"
        const val INSTANCE_ID = "INSTANCE_ID"
    }

    const val REQUEST_OAUTH = 404
    const val ITEM_NOT_FOUND = -1

    const val SHARED_PREF_LAST_SCREEN = "last_screen"
    const val SHARED_PREF_ACCESS_TOKEN = "access_token"
    const val SHARED_PREF_REFRESH_TOKEN = "refresh_token"
    const val SHARED_PREF_TOKEN_TYPE = "token_type"
    const val INSTANCE_ID = "instance_id"

    val VIDEO_RECORDING_SENSOR_LIST = intArrayOf(Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_LIGHT)

    val MIN_PASSWORD_CHARACTER_SIZE = 6
    val GARMIN_CONNECT_PACKAGE_NAME = "com.garmin.android.apps.connectmobile"

    const val MIGRAINE_IS_COMING = "migraine_is_coming"
    const val MIGRAINE_STARTED = "migraine_started"
    const val MIGRAINE_ENDED = "migraine_ended"
    const val MIGRAINE_DID_NOT_COME = "migraine_did_not_come"
    const val PERIOD_STARTED = "period_started"
    const val PERIOD_ENDED = "period_ended"

    const val UNKNOWN = "unknown"
    const val UNDEFINED = "undefined"

    const val EVENT_MIGRAINE_ENTERED = "migraine_entered"
    const val EVENT_MIGRAINE_UPDATED = "migraine_updated"
    const val EVENT_PERIOD_INFO_ENTERED = "period_info_entered"
    const val EVENT_PERIOD_INFO_UPDATED = "period_info_updated"
    const val EVENT_DAILY_ACTIVITY_ENTERED = "daily_activity_entered"
    const val EVENT_DAILY_ACTIVITY_UPDATED = "daily_activity_updated"
    const val EVENT_HYDRATION_STATUS_ENTERED = "hydration_status_entered"
    const val EVENT_MIGRAINE_MEDICATION_ADDED = "migraine_medication_added"
    const val EVENT_MIGRAINE_MEDICATION_UPDATED = "migraine_medication_updated"
    const val EVENT_REGULAR_MEDICATION_ADDED = "regular_medication_added"
    const val EVENT_REGULAR_MEDICATION_UPDATED = "regular_medication_updated"
    const val EVENT_MIGRAINE_MEDICATION_DELETED = "migraine_medication_deleted"
    const val EVENT_REGULAR_MEDICATION_DELETED = "regular_medication_deleted"
    const val EVENT_POP_UP_QUESTION_ANSWERED = "pop_up_question_answered"
    const val EVENT_LOG_OUT = "log_out"
    const val EVENT_SELFIE_CAPTURED = "selfie_captured"
    const val EVENT_SELFIE_CHOOSE_FROM_GALLERY = "selfie_choose_from_gallery"
    const val EVENT_SELFIE_SENDED = "selfie_sended"
    const val EVENT_VIDEO_RECORDED = "video_recorded"
    const val EVENT_VIDEO_SENDED = "video_sended"
    const val EVENT_VIDEO_CHOOSE_FROM_GALLERY = "video_choose_from_gallery"
    const val EVENT_REGULAR_MEDICATION_CAPTURED = "regular_medication_captured"
    const val EVENT_MIGRAINE_MEDICATION_CAPTURED = "migraine_medication_captured"
    const val EVENT_REGULAR_MEDICATION_PHOTO_ADDED = "regular_medication_photo_added"
    const val EVENT_MIGRAINE_MEDICATION_PHOTO_ADDED = "migraine_medication_photo_added"
    const val EVENT_MIGRAINE_SUMMARY_CLOSED = "migraine_summary_closed"
    const val EVENT_PERIOD_SUMMARY_CLOSED = "period_summary_closed"
    const val EVENT_MIGRAINE_SUMMARY_SUBMITTED = "migraine_summary_submitted"
    const val EVENT_PERIOD_SUMMARY_SUBMITTED = "period_summary_submitted"
    const val EVENT_MEDICATION_NAME_CONFIRMED = "medication_name_confirmed"
    const val EVENT_PROFILE_EDITED = "profile_edited"
    const val EVENT_WIDGET_CLICKED = "widget_clicked"
    const val EVENT_NOTIFICATION_CLICKED = "notification_clicked"
    const val EVENT_PUSH_NOTIFICATION_CLICKED = "push_notification_clicked"
    const val EVENT_VIDEO_UPLOAD_FAILED = "video_upload_failed"
    const val EVENT_SELFIE_UPLOAD_FAILED = "selfie_upload_failed"
    const val EVENT_EMAIL_UPDATED = "email_updated"
    const val EVENT_PHONE_NUMBER_UPDATED = "phone_number_updated"
    const val EVENT_PASSWORD_UPDATED = "password_updated"
    const val EVENT_CONTACT_US_SUBMITTED = "contact_us_submitted"
    const val EVENT_MEASUREMENT_UNITS_UPDATED = "measurement_units_updated"
    const val EVENT_SURVEY_PAGE_SAVED = "survey_page_saved"
    const val EVENT_PAGE_SEEN = "page_seen"
    const val EVENT_LOGIN = "login"
    const val EVENT_SIGN_UP = "sign_up"

    const val PARAM_STATUS = "status"
    const val PARAM_KEY = "key"
    const val PARAM_TYPE = "type"
    const val PARAM_REDIRECTION_TYPE = "redirection_type"
    const val PARAM_PAIN_SCALE = "pain_scale"
    const val PARAM_MEDICATIONS = "medication"
    const val PARAM_BLEEDING_AMOUNT = "bleeding_amount"
    const val PARAM_MEDICATION_TAKEN_TIME = "medication_taken_time"
    const val PARAM_EFFECT_CHANGES = "effect_changes"
    const val PARAM_EFFECT_CHANGES_OTHER = "effect_changes_other"
    const val PARAM_PAIN_AREAS = "pain_area"
    const val PARAM_ADDITIONAL_COMMENT = "additional_comment"
    const val PARAM_INFO = "info"
    const val PARAM_HOME = "home"
    const val PARAM_DAILY_ACTIVITIES = "daily_activities"
    const val PARAM_VIDEO = "video"
    const val PARAM_SELFIE = "selfie"
    const val PARAM_MIGRAINE = "migraine"
    const val PARAM_PERIOD_TRACKER = "period_tracker"
    const val PARAM_REGULAR_MEDICATION = "regular_medication"
    const val PARAM_SURVEY = "survey"
    const val PARAM_EDIT_PROFILE = "edit_profile"
    const val PARAM_CALENDAR = "calendar"
    const val PARAM_SETTINGS = "settings"
    const val PARAM_POP_UP_QUESTIONS = "popup_question"
    const val PARAM_REDIRECTION = "redirection"
    const val PARAM_REDIRECTION_WITH_QUESTION = "redirection_with_question"
    const val PARAM_DAILY_ACTIVITY_QUESTION = "daily_activity_question"
    const val PARAM_URL = "url"
    const val PARAM_APP_URL = "app_url"
    const val PARAM_HEIGHT = "height"
    const val PARAM_WEIGHT = "weight"
    const val PARAM_TEMPERATURE = "temperature"
    const val PARAM_HYDRATION_STATUS = "hydration_status"
    const val PARAM_MIGRAINE_DETAILS = "migraine_details"
    const val PARAM_MIGRAINE_SUMMARY = "migraine_summary"
    const val PARAM_PERIOD_TRACKER_DETAILS = "period_tracker_details"
    const val PARAM_PERIOD_TRACKER_SUMMARY = "period_tracker_summary"
    const val PARAM_NOTIFICATIONS = "notifications"
    const val PARAM_PROFILE = "profile"
    const val PARAM_MIGRAINE_MEDICATIONS = "migraine_medications"
    const val PARAM_REGULAR_MEDICATIONS = "regular_medications"
    const val PARAM_CONTACT_US = "contact_us"
    const val PARAM_CHANGE_EMAIL = "change_email"
    const val PARAM_CHANGE_PHONE_NUMBER = "change_phone_number"
    const val PARAM_CHANGE_PASSWORD = "change_password"
    const val PARAM_UNITS = "units"
    const val PARAM_FORGOT_PASSWORD = "forgot_password"
    const val PARAM_FORGOT_PASSWORD_OTP = "forgot_password_otp"
    const val PARAM_FORGOT_PASSWORD_NEW_PASSWORD = "forgot_password_new_password"
    const val PARAM_LOGIN = "login"
    const val PARAM_SIGN_UP = "sign_up"
    const val PARAM_REGULAR_MEDICATION_CAMERA = "regular_medication_camera"
    const val PARAM_MIGRAINE_MEDICATION_CAMERA = "migraine_medication_camera"
    const val PARAM_TERMS_OF_SERVICE = "terms_of_service"
    const val PARAM_LAST_PAGE = "last_page"

    const val CAMERA = "CAMERA"
    const val GALLERY = "GALLERY"
}