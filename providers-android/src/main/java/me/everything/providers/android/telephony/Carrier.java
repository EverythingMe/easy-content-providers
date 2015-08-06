package me.everything.providers.android.telephony;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony.Carriers;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class Carrier extends Entity {

    @IgnoreMapping
    public static Uri uri = Carriers.CONTENT_URI;

    @FieldMapping(columnName = Carriers.APN, physicalType = FieldMapping.PhysicalType.String)
    public String apn;

    @FieldMapping(columnName = Carriers.AUTH_TYPE, physicalType = FieldMapping.PhysicalType.Int)
    public int authType;

    @FieldMapping(columnName = Carriers.BEARER, physicalType = FieldMapping.PhysicalType.Int)
    public int bearer;

    @FieldMapping(columnName = Carriers.CARRIER_ENABLED, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean carrierEnabled;

    @FieldMapping(columnName = Carriers.CURRENT, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean current;

    @FieldMapping(columnName = Carriers.MCC, physicalType = FieldMapping.PhysicalType.String)
    public String mcc;

    @FieldMapping(columnName = Carriers.MMSC, physicalType = FieldMapping.PhysicalType.String)
    public String mmscUrl;

    @FieldMapping(columnName = Carriers.MMSPORT, physicalType = FieldMapping.PhysicalType.String)
    public String mmsPort;

    @FieldMapping(columnName = Carriers.MMSPROXY, physicalType = FieldMapping.PhysicalType.String)
    public String mmsProxy;

    @FieldMapping(columnName = Carriers.MNC, physicalType = FieldMapping.PhysicalType.String)
    public String mnc;

    @FieldMapping(columnName = Carriers.MVNO_MATCH_DATA, physicalType = FieldMapping.PhysicalType.String)
    public String mvnoMatchData;

    @FieldMapping(columnName = Carriers.MVNO_TYPE, physicalType = FieldMapping.PhysicalType.String)
    public String mvnoType;

    @FieldMapping(columnName = Carriers.NAME, physicalType = FieldMapping.PhysicalType.String)
    public String name;

    @FieldMapping(columnName = Carriers.NUMERIC, physicalType = FieldMapping.PhysicalType.String)
    public String numeric;

    @FieldMapping(columnName = Carriers.PASSWORD, physicalType = FieldMapping.PhysicalType.String)
    public String password;

    @FieldMapping(columnName = Carriers.PORT, physicalType = FieldMapping.PhysicalType.String)
    public String port;

    @FieldMapping(columnName = Carriers.PROTOCOL, physicalType = FieldMapping.PhysicalType.String)
    public String protocol;

    @FieldMapping(columnName = Carriers.ROAMING_PROTOCOL, physicalType = FieldMapping.PhysicalType.String)
    public String roamingProtocol;

    @FieldMapping(columnName = Carriers.SERVER, physicalType = FieldMapping.PhysicalType.String)
    public String server;

    @FieldMapping(columnName = Carriers.TYPE, physicalType = FieldMapping.PhysicalType.String)
    public String type;

    @FieldMapping(columnName = Carriers.USER, physicalType = FieldMapping.PhysicalType.String)
    public String user;
}
