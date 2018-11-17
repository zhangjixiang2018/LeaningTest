package screenbroadcast;

/**
 * 帧单元
 * FrameId : 每一帧的id,用时间戳表示，相同的帧的FrameId相同
 * UntilCount:帧单元的个数，表示每一帧被切成几个帧单元
 * UntilNo：帧单元的编号
 * DataLen:数据长度
 * Data[]:数据
 */
public class FUntil {
    private long frameId ;
    private int untilCount ;
    private int untilNo;
    private int dataLen ;
    private byte[] data ;

    public long getFrameId() {
        return frameId;
    }

    public void setFrameId(long frameId) {
        this.frameId = frameId;
    }

    public int getUntilCount() {
        return untilCount;
    }

    public void setUntilCount(int untilCount) {
        this.untilCount = untilCount;
    }

    public int getUntilNo() {
        return untilNo;
    }

    public void setUntilNo(int untilNo) {
        this.untilNo = untilNo;
    }

    public int getDataLen() {
        return dataLen;
    }

    public void setDataLen(int dataLen) {
        this.dataLen = dataLen;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
