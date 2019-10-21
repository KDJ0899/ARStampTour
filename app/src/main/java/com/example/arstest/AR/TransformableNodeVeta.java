package com.example.arstest.AR;

import com.google.ar.sceneform.ux.RotationController;
import com.google.ar.sceneform.ux.ScaleController;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.ar.sceneform.ux.TransformationSystem;

public class TransformableNodeVeta extends TransformableNode {

    private final TranslationControllerVeta translationControllerVeta;
    private final ScaleController scaleController;
    private final RotationController rotationController;

    @SuppressWarnings("initialization") // Suppress @UnderInitialization warning.
    public TransformableNodeVeta(TransformationSystem transformationSystem) {
        super(transformationSystem);

        translationControllerVeta =
                new TranslationControllerVeta(this, transformationSystem.getDragRecognizer());
        addTransformationController(translationControllerVeta);
        addTransformationController(translationControllerVeta);

        scaleController = new ScaleController(this, transformationSystem.getPinchRecognizer());
        scaleController.setMaxScale(10.0f);// 모델 크기 변경
        scaleController.setMinScale(0.1f);
        addTransformationController(scaleController);

        rotationController = new RotationController(this, transformationSystem.getTwistRecognizer());
        addTransformationController(rotationController);
    }
}
