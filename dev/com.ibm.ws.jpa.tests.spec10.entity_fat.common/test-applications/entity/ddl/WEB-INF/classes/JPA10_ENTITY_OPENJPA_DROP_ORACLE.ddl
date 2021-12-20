DROP TABLE ${schemaname}.SEQUENCE;

DROP SEQUENCE AnnSeqGen1_Seq;
DROP SEQUENCE XMLSeqGen1_Seq;
DROP SEQUENCE PKGenIdentityEntity_id_SEQ;
DROP SEQUENCE XMLPKGenIdentityEntity_id_SEQ;
DROP SEQUENCE SEQ_GEN_IDENTITY;

DROP INDEX ${schemaname}.I_SC_TBL1_ID;
DROP INDEX ${schemaname}.I_SC_TMSC_ID;
DROP INDEX ${schemaname}.I_SC_TLMB_ID;
DROP INDEX ${schemaname}.I_XSC_BL1_ID;
DROP INDEX ${schemaname}.I_XSC_MSC_ID;
DROP INDEX ${schemaname}.I_XSC_LMB_ID;

DROP TABLE ${schemaname}.AnnEmbedMultiTableEnt;
DROP TABLE ${schemaname}.AnnMSCMultiTableEnt;
DROP TABLE ${schemaname}.AnnMultiTableEnt;
DROP TABLE ${schemaname}.DatatypeSupPropTestEntity;
DROP TABLE ${schemaname}.DatatypeSupTestEntity;
DROP TABLE ${schemaname}.EmbeddableIdEntity;
DROP TABLE ${schemaname}.EmbeddedObjectAOEntity;
DROP TABLE ${schemaname}.EmbeddedObjectEntity;
DROP TABLE ${schemaname}.IdClassEntity;
DROP TABLE ${schemaname}.PKEntityByte;
DROP TABLE ${schemaname}.PKEntityByteWrapper;
DROP TABLE ${schemaname}.PKEntityChar;
DROP TABLE ${schemaname}.PKEntityCharacterWrapper;
DROP TABLE ${schemaname}.PKEntityInt;
DROP TABLE ${schemaname}.PKEntityIntWrapper;
DROP TABLE ${schemaname}.PKEntityJavaSqlDate;
DROP TABLE ${schemaname}.PKEntityJavaUtilDate;
DROP TABLE ${schemaname}.PKEntityLong;
DROP TABLE ${schemaname}.PKEntityLongWrapper;
DROP TABLE ${schemaname}.PKEntityShort;
DROP TABLE ${schemaname}.PKEntityShortWrapper;
DROP TABLE ${schemaname}.PKEntityString;
DROP TABLE ${schemaname}.PKGenAutoEntity;
DROP TABLE ${schemaname}.PKGenIdentityEntity;
DROP TABLE ${schemaname}.PKGenTableType1Entity;
DROP TABLE ${schemaname}.PKGenTableType2Entity;
DROP TABLE ${schemaname}.PKGenTableType3Entity;
DROP TABLE ${schemaname}.PKGenTableType4Entity;
DROP TABLE ${schemaname}.ReadOnlyEntity;
DROP TABLE ${schemaname}.SEC_TABLE1;
DROP TABLE ${schemaname}.SEC_TABLE2AMSC;
DROP TABLE ${schemaname}.SEC_TABLEEMB;
DROP TABLE ${schemaname}.SerialDatatypeSupPropTE;
DROP TABLE ${schemaname}.SerialDatatypeSupTE;
DROP TABLE ${schemaname}.TableIDGen4Table;
DROP TABLE ${schemaname}.TableIDGenTable;
DROP TABLE ${schemaname}.VersionedIntEntity;
DROP TABLE ${schemaname}.VersionedIntWrapperEntity;
DROP TABLE ${schemaname}.VersionedLongEntity;
DROP TABLE ${schemaname}.VersionedLongWrapperEntity;
DROP TABLE ${schemaname}.VersionedShortEntity;
DROP TABLE ${schemaname}.VersionedShortWrapperEntity;
DROP TABLE ${schemaname}.VersionedSqlTimestampEntity;

DROP TABLE ${schemaname}.XMLDatatypeSupPropTestEntity;
DROP TABLE ${schemaname}.XMLDatatypeSupTestEntity;
DROP TABLE ${schemaname}.XMLEmbeddableIdEntity;
DROP TABLE ${schemaname}.XMLEmbeddedObjectAOEntity;
DROP TABLE ${schemaname}.XMLEmbeddedObjectEntity;
DROP TABLE ${schemaname}.XMLEmbedMultiTableEnt;
DROP TABLE ${schemaname}.XMLIdClassEntity;
DROP TABLE ${schemaname}.XMLMSCMultiTableEnt;
DROP TABLE ${schemaname}.XMLMultiTableEnt;
DROP TABLE ${schemaname}.XMLPKEntityByte;
DROP TABLE ${schemaname}.XMLPKEntityByteWrapper;
DROP TABLE ${schemaname}.XMLPKEntityChar;
DROP TABLE ${schemaname}.XMLPKEntityCharacterWrapper;
DROP TABLE ${schemaname}.XMLPKEntityInt;
DROP TABLE ${schemaname}.XMLPKEntityIntWrapper;
DROP TABLE ${schemaname}.XMLPKEntityJavaSqlDate;
DROP TABLE ${schemaname}.XMLPKEntityJavaUtilDate;
DROP TABLE ${schemaname}.XMLPKEntityLong;
DROP TABLE ${schemaname}.XMLPKEntityLongWrapper;
DROP TABLE ${schemaname}.XMLPKEntityShort;
DROP TABLE ${schemaname}.XMLPKEntityShortWrapper;
DROP TABLE ${schemaname}.XMLPKEntityString;
DROP TABLE ${schemaname}.XMLPKGenAutoEntity;
DROP TABLE ${schemaname}.XMLPKGenIdentityEntity;
DROP TABLE ${schemaname}.XMLPKGenTableType1Entity;
DROP TABLE ${schemaname}.XMLPKGenTableType2Entity;
DROP TABLE ${schemaname}.XMLPKGenTableType3Entity;
DROP TABLE ${schemaname}.XMLPKGenTableType4Entity;
DROP TABLE ${schemaname}.XMLReadOnlyEntity;
DROP TABLE ${schemaname}.XMLSerialDatatypeSupPropTE;
DROP TABLE ${schemaname}.XMLSerialDatatypeSupTE;
DROP TABLE ${schemaname}.XMLTableIDGen4Table;
DROP TABLE ${schemaname}.XMLTableIDGenTable;
DROP TABLE ${schemaname}.XMLVersionedIntEntity;
DROP TABLE ${schemaname}.XMLVersionedIntWrapperEntity;
DROP TABLE ${schemaname}.XMLVersionedLongEntity;
DROP TABLE ${schemaname}.XMLVersionedLongWrapperEnt;
DROP TABLE ${schemaname}.XMLVersionedShortEntity;
DROP TABLE ${schemaname}.XMLVersionedShortWrapperEnt;
DROP TABLE ${schemaname}.XMLVersionedSqlTimestampEnt;
DROP TABLE ${schemaname}.XSEC_TABLE1;
DROP TABLE ${schemaname}.XSEC_TABLE2AMSC;
DROP TABLE ${schemaname}.XSEC_TABLEEMB;

DROP user jpaschema CASCADE;
