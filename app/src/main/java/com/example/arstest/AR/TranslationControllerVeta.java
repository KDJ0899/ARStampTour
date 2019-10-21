package com.example.arstest.AR;


import androidx.annotation.Nullable;

import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.MathHelper;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.ux.BaseTransformableNode;
import com.google.ar.sceneform.ux.DragGestureRecognizer;
import com.google.ar.sceneform.ux.TranslationController;

import java.util.EnumSet;

public class TranslationControllerVeta extends TranslationController {

    public TranslationControllerVeta(BaseTransformableNode transformableNode, DragGestureRecognizer gestureRecognizer) {
        super(transformableNode, gestureRecognizer);
    }

    @Nullable
    private HitResult lastArHitResult;
    @Nullable
    private Vector3 desiredLocalPosition;
    @Nullable
    private Quaternion desiredLocalRotation;

    private final Vector3 initialForwardInLocal = new Vector3();

    private EnumSet<Plane.Type> allowedPlaneTypes = EnumSet.allOf(Plane.Type.class);

    private static final float LERP_SPEED = 12.0f;
    private static final float POSITION_LENGTH_THRESHOLD = 0.01f;
    private static final float ROTATION_DOT_THRESHOLD = 0.99f;


    private AnchorNode getAnchorNodeOrDie() {
        Node parent = getTransformableNode().getParent();
        if (!(parent instanceof AnchorNode)) {
            throw new IllegalStateException("TransformableNode must have an AnchorNode as a parent.");
        }

        return (AnchorNode) parent;
    }

    private void updatePosition(FrameTime frameTime) {
        // Store in local variable for nullness static analysis.
        Vector3 desiredLocalPosition = this.desiredLocalPosition;
        if (desiredLocalPosition == null) {
            return;
        }

        Vector3 localPosition = getTransformableNode().getLocalPosition();
        float lerpFactor = MathHelper.clamp(frameTime.getDeltaSeconds() * LERP_SPEED, 0, 1);
        localPosition = Vector3.lerp(localPosition, desiredLocalPosition, lerpFactor);

        float lengthDiff = Math.abs(Vector3.subtract(desiredLocalPosition, localPosition).length());
        if (lengthDiff <= POSITION_LENGTH_THRESHOLD) {
            localPosition = desiredLocalPosition;
            this.desiredLocalPosition = null;
        }

        getTransformableNode().setLocalPosition(localPosition);
    }

    private void updateRotation(FrameTime frameTime) {
        // Store in local variable for nullness static analysis.
        Quaternion desiredLocalRotation = this.desiredLocalRotation;
        if (desiredLocalRotation == null) {
            return;
        }

        Quaternion localRotation = getTransformableNode().getLocalRotation();
        float lerpFactor = MathHelper.clamp(frameTime.getDeltaSeconds() * LERP_SPEED, 0, 1);
        localRotation = Quaternion.slerp(localRotation, desiredLocalRotation, lerpFactor);

        float dot = Math.abs(dotQuaternion(localRotation, desiredLocalRotation));
        if (dot >= ROTATION_DOT_THRESHOLD) {
            localRotation = desiredLocalRotation;
            this.desiredLocalRotation = null;
        }

        getTransformableNode().setLocalRotation(localRotation);
    }

    /**
     * When translating, the up direction of the node must match the up direction of the plane from
     * the hit result. However, we also need to make sure that the original forward direction of the
     * node is respected.
     */
    private Quaternion calculateFinalDesiredLocalRotation(Quaternion desiredLocalRotation) {
        // Get a rotation just to the up direction.
        // Otherwise, the node will spin around as you rotate.
        Vector3 rotatedUp = Quaternion.rotateVector(desiredLocalRotation, Vector3.up());
        desiredLocalRotation = Quaternion.rotationBetweenVectors(Vector3.up(), rotatedUp);

        // Adjust the rotation to make sure the node maintains the same forward direction.
        Quaternion forwardInLocal =
                Quaternion.rotationBetweenVectors(Vector3.forward(), initialForwardInLocal);
        desiredLocalRotation = Quaternion.multiply(desiredLocalRotation, forwardInLocal);

        return desiredLocalRotation.normalized();
    }


    private static float dotQuaternion(Quaternion lhs, Quaternion rhs) {
        return lhs.x * rhs.x + lhs.y * rhs.y + lhs.z * rhs.z + lhs.w * rhs.w;
    }
}

