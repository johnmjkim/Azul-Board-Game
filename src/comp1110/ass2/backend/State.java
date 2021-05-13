package comp1110.ass2.backend;

import comp1110.ass2.Constants;

public interface State extends Constants {

    /**
     * @author Min Jae, Kim
     * Get boolean true if state is empty
     * @return
     */
    boolean isStateEmpty();

    /**
     * @author Min Jae, Kim
     * Get String of state except its prefix
     * e.g. sharedState string : AF0acee1bdde2aabe3abbd4acdeCaabbdeB0606060608D0306040401
     * e.g. playerState string : A17Me00c01d02b03a04d10c12b14a20b21c23e24e32d43S3a34c3FeeeB16Me00b01d03a04d10e11a13b14d21b23a30e32d34a42S2c24c3Fdf
     * @return
     * Turn : A
     * Factory without 'F' : 0acee1bdde2aabe3abbd4acde
     * Center without 'C' : aabbde
     * Bag without 'B' : 0606060608
     * Discard without 'D' : 0306040401
     * PLayer A score : 17
     * PLayer A mosaic without 'M': e00c01d02b03a04d10c12b14a20b21c23e24e32d43
     * PLayer A storage without 'S': 3a34c3
     * Player A floor without 'F': eee
     * Player B as well
     */
    String getStateString();

    public String toString();

    void updateState();
}
