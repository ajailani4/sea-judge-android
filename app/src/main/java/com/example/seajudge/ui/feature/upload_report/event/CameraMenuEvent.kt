package com.example.seajudge.ui.feature.upload_report.event

sealed class CameraMenuEvent {
    object SwitchLens : CameraMenuEvent()
    object Capture : CameraMenuEvent()
    object ViewGallery : CameraMenuEvent()
}
