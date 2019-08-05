package com.beyond.sync;

import java.util.Date;

public class SyncStamp {
    public static final SyncStamp ZERO = create(new Date(0), new Date(0), new Date(0));

    private Date lastModifyTime;
    private Date lastSyncTimeStart;
    private Date lastSyncTimeEnd;

    public static SyncStamp create(Date lastModifyTime, Date lastSyncTime){
        SyncStamp syncStamp = new SyncStamp();
        syncStamp.setLastModifyTime(lastModifyTime == null?new Date(0):lastModifyTime);
        syncStamp.setLastSyncTimeEnd(lastSyncTime == null?new Date(0):lastSyncTime);
        return syncStamp;
    }

    public static SyncStamp create(Date lastModifyTime, Date lastSyncTimeStart, Date lastSyncTimeEnd){
        SyncStamp syncStamp = new SyncStamp();
        syncStamp.setLastModifyTime(lastModifyTime == null?new Date(0):lastModifyTime);
        syncStamp.setLastSyncTimeStart(lastSyncTimeStart == null?new Date(0):lastSyncTimeStart);
        syncStamp.setLastSyncTimeEnd(lastSyncTimeEnd == null?new Date(0):lastSyncTimeEnd);
        return syncStamp;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Date getLastSyncTimeEnd() {
        return lastSyncTimeEnd;
    }

    public void setLastSyncTimeEnd(Date lastSyncTimeEnd) {
        this.lastSyncTimeEnd = lastSyncTimeEnd;
    }

    public Date getLastSyncTimeStart() {
        return lastSyncTimeStart;
    }

    public void setLastSyncTimeStart(Date lastSyncTimeStart) {
        this.lastSyncTimeStart = lastSyncTimeStart;
    }
}
