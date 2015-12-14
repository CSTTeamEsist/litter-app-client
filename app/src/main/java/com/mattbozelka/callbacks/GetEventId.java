package com.mattbozelka.callbacks;

/**
 *
 * GetEventId
 *
 * Callback interface used to pass an event id from an activity to a fragment for data.
 *
 * Any activity loading a fragment that needs this data must implement this interface.
 *
 */
public interface GetEventId {
    public int getEventId();
}
