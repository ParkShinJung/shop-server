package com.example.shop.common.type;

public enum DataType {
    DO, // 용존산소량
    PH, // 산도
    TEMPERATURE, // 온도
    SALINITY, // 염분 농도

    // Calculated
    CALC_SUM_ISOL, // 누적 일사량
    CALC_SUM_ISOL_MB, // 누적 일사량(마스터벨그로우)
    CALC_MAX_TEMPERATURE, // 최고 온도
    CALC_MIN_TEMPERATURE, // 최저 온도
    CALC_DIF_TEMPERATURE, // 최고 최저 온도차
    CALC_AVG_TEMPERATURE, // 평균 온도

    // GreenCS
    CO2, // 이산화탄소 농도
    DHUM, // 이슬점
    DEWPOINT, // 이슬점
    GNDHUM, // 지습(FDR)
    GNDTEMP, // 지온(FDR)
    INHUM, // 내부습도
    INTEMP1, // 내부온도 1
    INTEMP2, // 내부온도 2
    INTEMP3, // 내부온도 3
    OUTTEMP, // 외부온도
    SUNADD, // 누적 일사량
    SUNVOL, // 일사량
    WINDDSP, // 풍속
    ABHUM, // 절대습도
    HUMLACK,    // 수분부족분
    RAIN, // 우적
    INHUM1, // 내부습도1

    INHUM2, // 내부습도2
    INHUM3, // 내부습도3
    WATERTEMP, // 순환온도(3Way1)
    WINDDIREC, // 풍향
    WINDSP, // 풍속
    VENTTEMP, // 제어환기온도
    HEATTEMP, // 제어난방온도
    STHUM, // 상대습도(포화수분)
    VENTTEMP2, // 이중창환기온도
    VENTCONT2, // 이중환기조절
    VENTRST, // 환기온도
    VENT2RST, // 이중환기온도
    HEATRST, // 난방온도
    WATERTEMP2, // 순환온도(3Way2)
    JUNGJUN, // 정전신호
    EC1, // EC1(FDR)
    EC2, // 급액 혹은 배액 EC ( ECPH 센서 )
    PH2, // 급액 혹은 배액 PH ( ECPH 센서 )


    // 마스터벨그로우
    ESI_0, // 이산화탄소
    ESI_1, // 강우량(예비)
    ESI_2, // 풍속(예비)
    ESI_3, // 풍향(예비)
    ESI_4, // 광량
    ESI_5, // 외부온도(예비)
    ESI_6, // 외부습도(예비)
    ESI_7, // PT100온도센서 0번(예비)
    ESI_8, // PT100온도센서 1번(건구)
    ESI_9, // PT100온도센서 2번(습구)
    ESI_10, // PT100온도센서 3번
    ESI_11, // PT100온도센서 4번(예비)
    ESI_12, // DS18 온도센서 0번(예비)
    ESI_13, // DS18 온도센서 1번(예비)
    ESI_14, // DS18 온도센서 2번(예비)
    ESI_15, // PT100 센서를 이용한 계산 습도
    ESO_0, // 이산화탄소
    ESO_1, // 강우량(예비)
    ESO_2, // 풍속(예비)
    ESO_3, // 풍향(예비)
    ESO_4, // 광량
    ESO_5, // 외부온도(예비)
    ESO_6, // 외부습도(예비)
    ESO_7, // PT100온도센서 0번(예비)
    ESO_8, // PT100온도센서 1번(건구)
    ESO_9, // PT100온도센서 2번(습구)
    ESO_10, // PT100온도센서 3번
    ESO_11, // PT100온도센서 4번(예비)
    ESO_12, // DS18 온도센서 0번(예비)
    ESO_13, // DS18 온도센서 1번(예비)
    ESO_14, // DS18 온도센서 2번(예비)
    ESO_15,  // PT100 센서를 이용한 계산 습도

    MSC_0,  // 공급 EC
    MSC_1,  // 배액 EC
    MSC_2,  // 공급 pH
    MSC_3,  // 배액 pH
    MSC_4,  // 저울 급액
    MSC_5,  // 저울 배액
    MSC_6,  // 온도 토양
    MSC_7,  // 온도 급액
    MSC_8,  // 온도 배액


    EC, // EC센서
    FLOWMETER, // 유량센서
    HD, // 습도부족분
    HUMID, // 상대 습도
    HUMIDITY, // 외부습도
    INSOLATION, // 일사량계
    PYRANOMETER, // 일사량
    RAIN_DETECTOR, // 우적
    SOIL_EC, // 토양EC-4
    SOIL_MOISTURE, // 토양함수율-4
    SOIL_TEMP, // 토양온도-4
    TEMP, // 온도
    TEMPLEAF, // 엽온
    TRANS_AMOUNT, // 증발산량
    TRANS_RATE, // 증발산률
    VPD, // 수증기압부족분
    VPDPLANT, // 식물수증기압부족분
    WEIGHT, // 중량
    WIND_DIRECTION, // 풍향
    WIND_SPEED, // 풍속
    CARBON,
    AVERAGE_DAILY_TEMPERATURE,
    DAY_AVERAGE_TEMPERATURE,
    NIGHT_AVERAGE_TEMPERATURE,
    DAY_AND_NIGHT_TEMPERATURE_DEVIATION,
    UNKNOWN; // 미등록 데이터

    DataType() {
    }

    public String krName() {
        String nameString = "UnKnown Data";

        switch (this) {
            case DO:
                nameString = "용존 산소량";
                break;
            case PH:
                nameString = "수소 이온 농도";
                break;
            case TEMPERATURE:
                nameString = "온도";
                break;
            case SALINITY:
                nameString = "염분 농도";
                break;
            case CO2:
                nameString = "이산화탄소 농도";
                break;
            case DHUM:
                nameString = "이슬점";
                break;
            case DEWPOINT:
                break;
            case GNDHUM:
                nameString = "지습(FDR)";
                break;
            case GNDTEMP:
                nameString = "지온(FDR)";
                break;
            case INHUM:
                nameString = "내부습도";
                break;
            case INTEMP1:
                nameString = "내부온도 1";
                break;
            case INTEMP2:
                nameString = "내부온도 2";
                break;
            case INTEMP3:
                nameString = "내부온도 3";
                break;
            case OUTTEMP:
                nameString = "외부온도";
                break;
            case SUNADD:
                nameString = "누적 일사량";
                break;
            case SUNVOL:
                nameString = "일사량";
                break;
            case ABHUM:
                nameString = "절대습도";
                break;
            case WINDDSP:
                nameString = "풍속";
                break;
            case MSC_0:
                nameString = "공급 EC";
                break;
            case MSC_1:
                nameString = "배액 EC";
                break;
            case MSC_2:
                nameString = "공급 pH";
                break;
            case MSC_3:
                nameString = "배액 pH";
                break;
            case HUMLACK:
                nameString = "수분부족분";
                break;
            case MSC_4:
                nameString = "저울 급액";
                break;
            case MSC_5:
                nameString = "저울 배액";
                break;
            case MSC_6:
                nameString = "온도 토양";
                break;
            case MSC_7:
                nameString = "온도 급액";
                break;
            case MSC_8:
                nameString = "온도 배액";
                break;
            case EC:
                nameString = "EC센서";
                break;
            case FLOWMETER:
                nameString = "유량센서";
                break;
            case HD:
                nameString = "습도부족분";
                break;
            case HUMID:
                nameString = "상대 습도";
                break;
            case HUMIDITY:
                nameString = "외부습도";
                break;
            case INSOLATION:
                nameString = "일사량계";
                break;
            case PYRANOMETER:
                nameString = "일사량";
                break;
            case RAIN:
                nameString = "우적";
                break;
            case RAIN_DETECTOR:
                break;
            case SOIL_EC:
                nameString = "토양EC-4";
                break;
            case SOIL_MOISTURE:
                nameString = "토양함수율-4";
                break;
            case SOIL_TEMP:
                nameString = "토양온도-4";
                break;
            case TEMP:
                nameString = "온도";
                break;
            case TEMPLEAF:
                nameString = "엽온";
                break;
            case TRANS_AMOUNT:
                nameString = "증발산량";
                break;
            case TRANS_RATE:
                nameString = "증발산률";
                break;
            case VPD:
                nameString = "수증기압부족분";
                break;
            case VPDPLANT:
                nameString = "식물수증기압부족분";
                break;
            case WEIGHT:
                nameString = "중량";
                break;
            case WIND_DIRECTION:
                nameString = "풍향";
                break;
            case CARBON:
                break;
            case AVERAGE_DAILY_TEMPERATURE:
                nameString = "일평균온도";
                break;
            case DAY_AVERAGE_TEMPERATURE:
                nameString = "주간평균온도";
                break;
            case NIGHT_AVERAGE_TEMPERATURE:
                nameString = "야간평균온도";
                break;
            case DAY_AND_NIGHT_TEMPERATURE_DEVIATION:
                nameString = "주야간온도편차";
                break;
            case WIND_SPEED:
                nameString = "풍속";
                break;
            case INHUM1:
                nameString = "내부습도1";
                break;
            case INHUM2:
                nameString = "내부습도2";
                break;
            case INHUM3:
                nameString = "내부습도3";
                break;
            case WATERTEMP:
                nameString = "순환온도(3Way1)";
                break;
            case WINDDIREC:
                nameString = "풍향";
                break;
            case WINDSP:
                nameString = "풍속";
                break;
            case VENTTEMP:
                nameString = "제어환기온도";
                break;
            case HEATTEMP:
                nameString = "제어난방온도";
                break;
            case STHUM:
                nameString = "상대습도(포화수분)";
                break;
            case VENTTEMP2:
                nameString = "이중창환기온도";
                break;
            case VENTCONT2:
                nameString = "이중환기조절";
                break;
            case VENTRST:
                nameString = "환기온도";
                break;
            case VENT2RST:
                nameString = "이중환기온도";
                break;
            case HEATRST:
                nameString = "난방온도";
                break;
            case WATERTEMP2:
                nameString = "순환온도(3Way2)";
                break;
            case JUNGJUN:
                nameString = "정전신호";
                break;
            case EC1:
                nameString = "EC1(FDR)";
                break;
            case EC2:
                nameString = "급액 혹은 배액 EC ( ECPH 센서 )";
                break;
            case PH2:
                nameString = "급액 혹은 배액 PH ( ECPH 센서 )";
                break;
            case ESI_0:
                nameString = "이산화탄소";
                break;
            case ESI_1:
                nameString = "강우량(예비)";
                break;
            case ESI_2:
                nameString = "풍속(예비)";
                break;
            case ESI_3:
                nameString = "풍향(예비)";
                break;
            case ESI_4:
                nameString = "광량";
                break;
            case ESI_5:
                nameString = "외부온도(예비)";
                break;
            case ESI_6:
                nameString = "외부습도(예비)";
                break;
            case ESI_7:
                nameString = "PT100온도센서 0번(예비)";
                break;
            case ESI_8:
                nameString = "PT100온도센서 1번(건구)";
                break;
            case ESI_9:
                nameString = "PT100온도센서 2번(습구)";
                break;
            case ESI_10:
                nameString = "PT100온도센서 3번";
                break;
            case ESI_11:
                nameString = "PT100온도센서 4번(예비)";
                break;
            case ESI_12:
                nameString = "DS18 온도센서 0번(예비)";
                break;
            case ESI_13:
                nameString = "DS18 온도센서 1번(예비)";
                break;
            case ESI_14:
                nameString = "DS18 온도센서 2번(예비)";
                break;
            case ESI_15:
                nameString = "PT100 센서를 이용한 계산 습도";
                break;
            case ESO_0:
                nameString = "이산화탄소";
                break;
            case ESO_1:
                nameString = "강우량(예비)";
                break;
            case ESO_2:
                nameString = "풍속(예비)";
                break;
            case ESO_3:
                nameString = "풍향(예비)";
                break;
            case ESO_4:
                nameString = "광량";
                break;
            case ESO_5:
                nameString = "외부온도(예비)";
                break;
            case ESO_6:
                nameString = "외부습도(예비)";
                break;
            case ESO_7:
                nameString = "PT100온도센서 0번(예비)";
                break;
            case ESO_8:
                nameString = "PT100온도센서 1번(건구)";
                break;
            case ESO_9:
                nameString = "PT100온도센서 2번(습구)";
                break;
            case ESO_10:
                nameString = "PT100온도센서 3번";
                break;
            case ESO_11:
                nameString = "PT100온도센서 4번(예비)";
                break;
            case ESO_12:
                nameString = "DS18 온도센서 0번(예비)";
                break;
            case ESO_13:
                nameString = "DS18 온도센서 1번(예비)";
                break;
            case ESO_14:
                nameString = "DS18 온도센서 2번(예비)";
                break;
            case ESO_15:
                nameString = "PT100 센서를 이용한 계산 습도";
                break;

            case UNKNOWN:
                break;
            default:
        }

        return nameString;
    }

    public String enName() {
        String nameString = "UnKnown Data";

        switch (this) {
            case DO:
                nameString = "용존 산소량";
                break;
            case PH:
                nameString = "수소 이온 농도";
                break;
            case TEMPERATURE:
                nameString = "온도";
                break;
            case SALINITY:
                nameString = "염분 농도";
                break;
            case CO2:
                nameString = "이산화탄소 농도";
                break;
            case DHUM:
                nameString = "이슬점";
                break;
            case DEWPOINT:
                break;
            case GNDHUM:
                nameString = "지습(FDR)";
                break;
            case GNDTEMP:
                nameString = "지온(FDR)";
                break;
            case INHUM:
                nameString = "내부습도";
                break;
            case INTEMP1:
                nameString = "내부온도 1";
                break;
            case INTEMP2:
                nameString = "내부온도 2";
                break;
            case INTEMP3:
                nameString = "내부온도 3";
                break;
            case OUTTEMP:
                nameString = "외부온도";
                break;
            case SUNADD:
                nameString = "누적 일사량";
                break;
            case SUNVOL:
                nameString = "일사량";
                break;
            case WINDDSP:
                nameString = "풍속";
                break;
            case EC:
                nameString = "EC센서";
                break;
            case FLOWMETER:
                nameString = "유량센서";
                break;
            case HD:
                nameString = "습도부족분";
                break;
            case HUMID:
                nameString = "상대 습도";
                break;
            case HUMIDITY:
                nameString = "외부습도";
                break;
            case INSOLATION:
                nameString = "일사량계";
                break;
            case PYRANOMETER:
                nameString = "일사량";
                break;
            case ABHUM:
                break;
            case HUMLACK:
                break;
            case RAIN:
                nameString = "우적";
                break;
            case SOIL_EC:
                nameString = "토양EC-4";
                break;
            case SOIL_MOISTURE:
                nameString = "토양함수율-4";
                break;
            case SOIL_TEMP:
                nameString = "토양온도-4";
                break;
            case TEMP:
                nameString = "온도";
                break;
            case TEMPLEAF:
                nameString = "엽온";
                break;
            case TRANS_AMOUNT:
                nameString = "증발산량";
                break;
            case TRANS_RATE:
                nameString = "증발산률";
                break;
            case VPD:
                nameString = "수증기압부족분";
                break;
            case VPDPLANT:
                nameString = "식물수증기압부족분";
                break;
            case WEIGHT:
                nameString = "중량";
                break;
            case WIND_DIRECTION:
                nameString = "풍향";
                break;
            case WIND_SPEED:
                nameString = "풍속";
                break;
            case AVERAGE_DAILY_TEMPERATURE:
                nameString = "일평균온도";
                break;
            case DAY_AVERAGE_TEMPERATURE:
                nameString = "주간평균온도";
                break;
            case NIGHT_AVERAGE_TEMPERATURE:
                nameString = "야간평균온도";
                break;
            case DAY_AND_NIGHT_TEMPERATURE_DEVIATION:
                nameString = "주야간온도편차";
                break;

            case INHUM1:
                nameString = "내부습도1";
                break;
            case INHUM2:
                nameString = "내부습도2";
                break;
            case INHUM3:
                nameString = "내부습도3";
                break;
            case WATERTEMP:
                nameString = "순환온도(3Way1)";
                break;
            case WINDDIREC:
                nameString = "풍향";
                break;
            case WINDSP:
                nameString = "풍속";
                break;
            case VENTTEMP:
                nameString = "제어환기온도";
                break;
            case HEATTEMP:
                nameString = "제어난방온도";
                break;
            case STHUM:
                nameString = "상대습도(포화수분)";
                break;
            case VENTTEMP2:
                nameString = "이중창환기온도";
                break;
            case VENTCONT2:
                nameString = "이중환기조절";
                break;
            case VENTRST:
                nameString = "환기온도";
                break;
            case VENT2RST:
                nameString = "이중환기온도";
                break;
            case HEATRST:
                nameString = "난방온도";
                break;
            case WATERTEMP2:
                nameString = "순환온도(3Way2)";
                break;
            case JUNGJUN:
                nameString = "정전신호";
                break;
            case EC1:
                nameString = "EC1(FDR)";
                break;
            case EC2:
                nameString = "급액 혹은 배액 EC ( ECPH 센서 )";
                break;
            case PH2:
                nameString = "급액 혹은 배액 PH ( ECPH 센서 )";
                break;
            case MSC_0:
                nameString = "공급 EC";
                break;
            case MSC_1:
                nameString = "배액 EC";
                break;
            case MSC_2:
                nameString = "공급 pH";
                break;
            case MSC_3:
                nameString = "배액 pH";
                break;
            case MSC_4:
                nameString = "저울 급액";
                break;
            case MSC_5:
                nameString = "저울 배액";
                break;
            case MSC_6:
                nameString = "온도 토양";
                break;
            case MSC_7:
                nameString = "온도 급액";
                break;
            case MSC_8:
                nameString = "온도 배액";
                break;
            case RAIN_DETECTOR:
                nameString = "우적";
                break;
            case CARBON:
                nameString = "Carbon";
                break;
            case ESI_0:
                nameString = "이산화탄소";
                break;
            case ESI_1:
                nameString = "강우량(예비)";
                break;
            case ESI_2:
                nameString = "풍속(예비)";
                break;
            case ESI_3:
                nameString = "풍향(예비)";
                break;
            case ESI_4:
                nameString = "광량";
                break;
            case ESI_5:
                nameString = "외부온도(예비)";
                break;
            case ESI_6:
                nameString = "외부습도(예비)";
                break;
            case ESI_7:
                nameString = "PT100온도센서 0번(예비)";
                break;
            case ESI_8:
                nameString = "PT100온도센서 1번(건구)";
                break;
            case ESI_9:
                nameString = "PT100온도센서 2번(습구)";
                break;
            case ESI_10:
                nameString = "PT100온도센서 3번";
                break;
            case ESI_11:
                nameString = "PT100온도센서 4번(예비)";
                break;
            case ESI_12:
                nameString = "DS18 온도센서 0번(예비)";
                break;
            case ESI_13:
                nameString = "DS18 온도센서 1번(예비)";
                break;
            case ESI_14:
                nameString = "DS18 온도센서 2번(예비)";
                break;
            case ESI_15:
                nameString = "PT100 센서를 이용한 계산 습도";
                break;
            case ESO_0:
                nameString = "이산화탄소";
                break;
            case ESO_1:
                nameString = "강우량(예비)";
                break;
            case ESO_2:
                nameString = "풍속(예비)";
                break;
            case ESO_3:
                nameString = "풍향(예비)";
                break;
            case ESO_4:
                nameString = "광량";
                break;
            case ESO_5:
                nameString = "외부온도(예비)";
                break;
            case ESO_6:
                nameString = "외부습도(예비)";
                break;
            case ESO_7:
                nameString = "PT100온도센서 0번(예비)";
                break;
            case ESO_8:
                nameString = "PT100온도센서 1번(건구)";
                break;
            case ESO_9:
                nameString = "PT100온도센서 2번(습구)";
                break;
            case ESO_10:
                nameString = "PT100온도센서 3번";
                break;
            case ESO_11:
                nameString = "PT100온도센서 4번(예비)";
                break;
            case ESO_12:
                nameString = "DS18 온도센서 0번(예비)";
                break;
            case ESO_13:
                nameString = "DS18 온도센서 1번(예비)";
                break;
            case ESO_14:
                nameString = "DS18 온도센서 2번(예비)";
                break;
            case ESO_15:
                nameString ="PT100 센서를 이용한 계산 습도";
                break;

            case UNKNOWN:
                nameString = "Unknown";
                break;
            default:
        }

        return nameString;
    }

    public String dataFileName() {
        String fieldName = "UNKNOWN";

        switch (this) {
            case DO:
                fieldName = "DO";
                break;
            case PH:
                fieldName = "PH";
                break;
            case TEMPERATURE:
                fieldName = "TEMPERATURE";
                break;
            case SALINITY:
                fieldName = "SALINITY";
                break;
            case CO2:
                fieldName = "XCO2";
                break;
            case DHUM:
                fieldName = "XDHUM";
                break;
            case DEWPOINT:
                break;
            case GNDHUM:
                fieldName = "XGNDHUM";
                break;
            case GNDTEMP:
                fieldName = "XGNDTEMP";
                break;
            case INHUM:
                fieldName = "XINHUM";
                break;
            case INTEMP1:
                fieldName = "XINTEMP1";
                break;
            case INTEMP2:
                fieldName = "XINTEMP2";
                break;
            case INTEMP3:
                fieldName = "XINTEMP3";
                break;
            case OUTTEMP:
                fieldName = "XOUTTEMP";
                break;
            case SUNADD:
                fieldName = "XSUNADD";
                break;
            case SUNVOL:
                fieldName = "XSUNVOL";
                break;
            case WINDDSP:
                fieldName = "XWINDDSP";
                break;
            case ABHUM:
                fieldName = "XABHUM";
                break;
            case MSC_0:
                fieldName = "MSC_0";
                break;
            case MSC_1:
                fieldName = "MSC_1";
                break;
            case MSC_2:
                fieldName = "MSC_2";
                break;
            case MSC_3:
                fieldName = "MSC_3";
                break;
            case HUMLACK:
                fieldName = "XHUMLACK";
                break;

            case RAIN:
                break;
            case INHUM1:
                fieldName = "XINHUM1";
                break;
            case INHUM2:
                fieldName = "XINHUM2";
                break;
            case INHUM3:
                fieldName = "XINHUM3";
                break;
            case WATERTEMP:
                fieldName = "XWATERTEMP";
                break;
            case WINDDIREC:
                fieldName = "XWINDDIREC";
                break;
            case WINDSP:
                fieldName = "XWINDSP";
                break;
            case VENTTEMP:
                fieldName = "XVENTTEMP";
                break;
            case HEATTEMP:
                fieldName = "XHEATTEMP";
                break;
            case STHUM:
                fieldName = "XSTHUM";
                break;
            case VENTTEMP2:
                fieldName = "XVENTTEMP2";
                break;
            case VENTCONT2:
                fieldName = "XVENTCONT2";
                break;
            case VENTRST:
                fieldName = "XVENTRST";
                break;
            case VENT2RST:
                fieldName = "XVENT2RST";
                break;
            case HEATRST:
                fieldName = "XHEATRST";
                break;
            case WATERTEMP2:
                fieldName = "XWATERTEMP2";
                break;
            case JUNGJUN:
                fieldName = "XJUNGJUN";
                break;
            case EC1:
                fieldName = "XEC1";
                break;
            case EC2:
                fieldName = "XEC2";
                break;
            case PH2:
                fieldName = "XPH2";
                break;
            case MSC_4:
                fieldName = "MSC_4";
                break;
            case MSC_5:
                fieldName = "MSC_5";
                break;
            case MSC_6:
                fieldName = "MSC_6";
                break;
            case MSC_7:
                fieldName = "MSC_7";
                break;
            case MSC_8:
                fieldName = "MSC_8";
                break;
            case EC:
                fieldName = "EC";
                break;
            case FLOWMETER:
                fieldName = "FLOWMETER";
                break;
            case HD:
                fieldName = "HD";
                break;
            case HUMID:
                fieldName = "HUMID";
                break;
            case HUMIDITY:
                fieldName = "HUMIDITY";
                break;
            case INSOLATION:
                fieldName = "INSOLATION";
                break;
            case PYRANOMETER:
                fieldName = "PYRANOMETER";
                break;
            case RAIN_DETECTOR:
                fieldName = "RAIN_DETECTOR";
                break;
            case SOIL_EC:
                fieldName = "SOIL_EC";
                break;
            case SOIL_MOISTURE:
                fieldName = "SOIL_MOISTURE";
                break;
            case SOIL_TEMP:
                fieldName = "SOIL_TEMP";
                break;
            case TEMP:
                fieldName = "TEMP";
                break;
            case TEMPLEAF:
                fieldName = "TEMPLEAF";
                break;
            case TRANS_AMOUNT:
                fieldName = "TRANS_AMOUNT";
                break;
            case TRANS_RATE:
                fieldName = "TRANS_RATE";
                break;
            case VPD:
                fieldName = "VPD";
                break;
            case VPDPLANT:
                fieldName = "VPDPLANT";
                break;
            case WEIGHT:
                fieldName = "WEIGHT";
                break;
            case WIND_DIRECTION:
                fieldName = "WIND_DIRECTION";
                break;
            case WIND_SPEED:
                fieldName = "WIND_SPEED";
                break;
            case CARBON:
                fieldName = "CARBON";
                break;
            case AVERAGE_DAILY_TEMPERATURE:
                fieldName = "AVERAGE_DAILY_TEMPERATURE";
                break;
            case DAY_AVERAGE_TEMPERATURE:
                fieldName = "DAY_AVERAGE_TEMPERATURE";
                break;
            case NIGHT_AVERAGE_TEMPERATURE:
                fieldName = "NIGHT_AVERAGE_TEMPERATURE";
                break;
            case DAY_AND_NIGHT_TEMPERATURE_DEVIATION:
                fieldName = "DAY_AND_NIGHT_TEMPERATURE_DEVIATION";
                break;
            case ESI_0:
                fieldName = "이산화탄소";
                break;
            case ESI_1:
                fieldName = "강우량(예비)";
                break;
            case ESI_2:
                fieldName = "풍속(예비)";
                break;
            case ESI_3:
                fieldName = "풍향(예비)";
                break;
            case ESI_4:
                fieldName = "광량";
                break;
            case ESI_5:
                fieldName = "외부온도(예비)";
                break;
            case ESI_6:
                fieldName = "외부습도(예비)";
                break;
            case ESI_7:
                fieldName = "PT100온도센서 0번(예비)";
                break;
            case ESI_8:
                fieldName = "PT100온도센서 1번(건구)";
                break;
            case ESI_9:
                fieldName = "PT100온도센서 2번(습구)";
                break;
            case ESI_10:
                fieldName = "PT100온도센서 3번";
                break;
            case ESI_11:
                fieldName = "PT100온도센서 4번(예비)";
                break;
            case ESI_12:
                fieldName = "DS18 온도센서 0번(예비)";
                break;
            case ESI_13:
                fieldName = "DS18 온도센서 1번(예비)";
                break;
            case ESI_14:
                fieldName = "DS18 온도센서 2번(예비)";
                break;
            case ESI_15:
                fieldName = "PT100 센서를 이용한 계산 습도";
                break;
            case ESO_0:
                fieldName = "이산화탄소";
                break;
            case ESO_1:
                fieldName = "강우량(예비)";
                break;
            case ESO_2:
                fieldName = "풍속(예비)";
                break;
            case ESO_3:
                fieldName = "풍향(예비)";
                break;
            case ESO_4:
                fieldName = "광량";
                break;
            case ESO_5:
                fieldName = "외부온도(예비)";
                break;
            case ESO_6:
                fieldName = "외부습도(예비)";
                break;
            case ESO_7:
                fieldName = "PT100온도센서 0번(예비)";
                break;
            case ESO_8:
                fieldName = "PT100온도센서 1번(건구)";
                break;
            case ESO_9:
                fieldName = "PT100온도센서 2번(습구)";
                break;
            case ESO_10:
                fieldName = "PT100온도센서 3번";
                break;
            case ESO_11:
                fieldName = "PT100온도센서 4번(예비)";
                break;
            case ESO_12:
                fieldName = "DS18 온도센서 0번(예비)";
                break;
            case ESO_13:
                fieldName = "DS18 온도센서 1번(예비)";
                break;
            case ESO_14:
                fieldName = "DS18 온도센서 2번(예비)";
                break;
            case ESO_15:
                fieldName = "PT100 센서를 이용한 계산 습도";
                break;

            case UNKNOWN:
                break;
            default:
        }

        return fieldName;
    }
    public String equipCompId() {
        String equipCompId = "UNKNOWN";

        switch (this) {
            case DO:
            case PH:
            case TEMPERATURE:
            case SALINITY:
                equipCompId = "WaterTank";
                break;
            case DEWPOINT:
                break;
            case RAIN:
                break;
            case CO2:
            case DHUM:
            case GNDHUM:
            case GNDTEMP:
            case INHUM:
            case INTEMP1:
            case INTEMP2:
            case INTEMP3:
            case OUTTEMP:
            case SUNADD:
            case SUNVOL:
            case WINDDSP:
            case ABHUM:
            case HUMLACK:
            case INHUM1:
            case INHUM2:
            case INHUM3:
            case WATERTEMP:
            case WINDDIREC:
            case WINDSP:
            case VENTTEMP:
            case HEATTEMP:
            case STHUM:
            case VENTTEMP2:
            case VENTCONT2:
            case VENTRST:
            case VENT2RST:
            case HEATRST:
            case WATERTEMP2:
            case JUNGJUN:
            case EC1:
            case EC2:
            case PH2:
                equipCompId = "ECRP0002";
                break;
            case MSC_0:
            case MSC_1:
            case MSC_2:
            case MSC_3:
            case MSC_4:
            case MSC_5:
            case MSC_6:
            case MSC_7:
            case MSC_8:
            case EC:
            case ESO_15:
            case ESO_6:
            case ESI_0:
            case ESI_1:
            case ESI_13:
            case ESI_12:
            case ESI_11:
            case ESI_10:
            case ESI_9:
            case ESI_8:
            case ESI_7:
            case ESI_6:
            case ESI_5:
            case ESI_4:
            case ESI_14:
            case ESI_3:
            case ESI_2:
            case ESO_14:
            case ESO_13:
            case ESO_12:
            case ESO_11:
            case ESO_10:
            case ESO_9:
            case ESO_8:
            case ESO_7:
            case ESO_5:
            case ESO_4:
            case ESO_3:
            case ESO_2:
            case ESO_1:
            case ESO_0:
            case ESI_15:
                equipCompId = "ECRP0003";
                break;
            case FLOWMETER:
                break;
            case HD:
                break;
            case HUMID:
                break;
            case HUMIDITY:
                break;
            case INSOLATION:
                break;
            case PYRANOMETER:
                break;
            case RAIN_DETECTOR:
                break;
            case SOIL_EC:
                break;
            case SOIL_MOISTURE:
                break;
            case SOIL_TEMP:
                break;
            case TEMP:
                break;
            case TEMPLEAF:
                break;
            case TRANS_AMOUNT:
                break;
            case TRANS_RATE:
                break;
            case VPD:
                break;
            case VPDPLANT:
                break;
            case WEIGHT:
                break;
            case WIND_DIRECTION:
                break;
            case WIND_SPEED:
                break;
            case CARBON:
                break;
            case AVERAGE_DAILY_TEMPERATURE:
                equipCompId = "AVERAGE_DAILY_TEMPERATURE";
                break;
            case DAY_AVERAGE_TEMPERATURE:
                equipCompId = "DAY_AVERAGE_TEMPERATURE";
                break;
            case NIGHT_AVERAGE_TEMPERATURE:
                equipCompId = "NIGHT_AVERAGE_TEMPERATURE";
                break;
            case DAY_AND_NIGHT_TEMPERATURE_DEVIATION:
                equipCompId = "DAY_AND_NIGHT_TEMPERATURE_DEVIATION";
                break;


            case UNKNOWN:
                break;
            default:
        }

        return equipCompId;
    }

    public String unit() {
        String unitString = "UnKnown unit";

        switch (this) {
            case DO:
                unitString = "mg/l";
                break;

            // PH
            case PH:
            case PH2:
            case MSC_2:
            case MSC_3:
                unitString = "ph";
                break;
            // 온도
            case TEMPERATURE:
            case SOIL_TEMP:
            case TEMPLEAF:
            case DHUM:
            case DEWPOINT:
            case GNDTEMP:
            case INTEMP1:
            case INTEMP2:
            case INTEMP3:
            case OUTTEMP:
            case TEMP:
            case AVERAGE_DAILY_TEMPERATURE:
            case DAY_AVERAGE_TEMPERATURE:
            case NIGHT_AVERAGE_TEMPERATURE:
            case DAY_AND_NIGHT_TEMPERATURE_DEVIATION:
            case WATERTEMP:
            case VENTTEMP:
            case HEATTEMP:
            case VENTTEMP2:
            case VENTCONT2:
            case VENTRST:
            case VENT2RST:
            case HEATRST:
            case WATERTEMP2:
            case MSC_7:
            case MSC_8:
            case ESO_7: // PT100온도센서 0번(예비)
            case ESO_8: // PT100온도센서 1번(건구)
            case ESO_9: // PT100온도센서 2번(습구)
            case ESO_10: // PT100온도센서 3번
            case ESO_11: // PT100온도센서 4번(예비)
            case ESO_12: // DS18 온도센서 0번(예비)
            case ESO_13: // DS18 온도센서 1번(예비)
            case ESO_14: // DS18 온도센서 2번(예비)
            case ESI_7: // PT100온도센서 0번(예비)
            case ESI_8: // PT100온도센서 1번(건구)
            case ESI_9: // PT100온도센서 2번(습구)
            case ESI_10: // PT100온도센서 3번
            case ESI_11: // PT100온도센서 4번(예비)
            case ESI_12: // DS18 온도센서 0번(예비)
            case ESI_13: // DS18 온도센서 1번(예비)
            case ESI_14: // DS18 온도센서 2번(예비)
            case ESO_5: // 외부온도(예비)
            case ESI_5: // 내부온도(예비)
                unitString = "℃";
                break;

            // 기압
            case VPD:
            case VPDPLANT:
                unitString = "kPa";
                break;

            // 습도
            case HUMID:
            case SALINITY:
            case GNDHUM: // 지습
            case SOIL_MOISTURE:
            case INHUM:  // 내부습도
            case HD:
            case HUMLACK:
            case INHUM1:
            case INHUM2:
            case INHUM3:
            case ESI_6: // 외부습도(예비)
            case ESO_6: // 외부습도(예비)
            case HUMIDITY:
                unitString = "%";
                break;

            // 절대습도
            case ABHUM:
            case STHUM:
            case ESI_15: // PT100 센서를 이용한 계산 습도
            case ESO_15:  // PT100 센서를 이용한 계산 습도
                unitString = "g/m³";
                break;

            // CO2 농도
            case CARBON:
            case ESI_0: // 이산화탄소
            case ESO_0: // 이산화탄소
            case CO2:
                unitString = "ppm";
                break;

            // 누적 일사량
            case CALC_SUM_ISOL:
            case CALC_SUM_ISOL_MB:
            case SUNADD: // 누적 일사량
                unitString = "J/cm²";
                break;

            // 일사량
            case INSOLATION:
            case PYRANOMETER:
            case ESI_4: // 광량
            case ESO_4: // 광량
            case SUNVOL: // 순간 일사량
//                unitString = "μmol/m²";
                unitString = "w";
                break;

            // 속도
            case WIND_SPEED:
            case WINDDSP:
            case WINDSP:
            case ESI_2: // 풍속(예비)
            case ESO_2: // 풍속(예비)
                unitString = "m/s";
                break;

            // EC
            case MSC_4:
            case MSC_5:
            case MSC_6:
            case EC:
            case EC1:
            case EC2:
            case MSC_0:
            case MSC_1:
            case SOIL_EC:
                unitString = "ms/cm";
                break;

            // 강우량
            case ESI_1: // 강우량(예비)
            case ESO_1: // 강우량(예비)
            case RAIN:
                unitString = "mm";
                break;

            // 유량
            case FLOWMETER:
                unitString = "ccm";
                break;

            // 증발 산량
            case TRANS_AMOUNT:
                unitString = "cm/day";
                break;

            // 증발 산율
            case TRANS_RATE:
                unitString = "mm/day";
                break;

            // 중량
            case WEIGHT:
                unitString = "g";

                // 단위 없음
            case ESI_3: // 풍향(예비)
            case ESO_3: // 풍향(예비)
            case WIND_DIRECTION:
            case WINDDIREC:
            case JUNGJUN:
            case RAIN_DETECTOR:
                unitString = "-";
                break;

            case UNKNOWN:
            default:
                unitString = "";
        }

        return unitString;
    }

}
