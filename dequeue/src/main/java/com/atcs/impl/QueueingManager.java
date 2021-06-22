package com.atcs.impl;

import com.atcs.data.DataDelegate;
import com.atcs.objects.DequeueResult;
import com.atcs.objects.Plane;
import com.atcs.objects.Status;

import java.util.ArrayList;
import java.util.List;

public class QueueingManager {


    List<Plane> planeList = new ArrayList<>();

    /**
     * This method removes the plane from the queue.
     * @return Status if Successful
     */
    public DequeueResult dequeuePlane(String connection) {
        DataDelegate dataDelegate = new DataDelegate(connection);
        DequeueResult dequeueResult = dataDelegate.dequeuePlane();
        return dequeueResult;
    }

}
