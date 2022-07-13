package com.example.exchangeratestracking.domain.mapper

import com.example.exchangeratestracking.data.remote.responce.CurrentRateResponce
import com.example.exchangeratestracking.presentation.entity.ExchangeRate
import javax.inject.Inject

class CurrentRateResponseToMapExchangeRateMapper @Inject constructor() : (CurrentRateResponce) -> List<ExchangeRate> {

    override fun invoke(currentRateResponce: CurrentRateResponce): List<ExchangeRate> {

        return listOf(
            ExchangeRate("AED", currentRateResponce.rates.AED),
            ExchangeRate("AFN", currentRateResponce.rates.AFN),
            ExchangeRate("ALL", currentRateResponce.rates.ALL),
            ExchangeRate("AMD", currentRateResponce.rates.AMD),
            ExchangeRate("ANG", currentRateResponce.rates.ANG),
            ExchangeRate("AOA", currentRateResponce.rates.AOA),
            ExchangeRate("ARS", currentRateResponce.rates.ARS),
            ExchangeRate("AUD", currentRateResponce.rates.AUD),
            ExchangeRate("AWG", currentRateResponce.rates.AWG),
            ExchangeRate("AZN", currentRateResponce.rates.AZN),
            ExchangeRate("BAM", currentRateResponce.rates.BAM),
            ExchangeRate("BBD", currentRateResponce.rates.BBD),
            ExchangeRate("BDT", currentRateResponce.rates.BDT),
            ExchangeRate("BGN", currentRateResponce.rates.BGN),
            ExchangeRate("BHD", currentRateResponce.rates.BHD),
            ExchangeRate("BIF", currentRateResponce.rates.BIF),
            ExchangeRate("BMD", currentRateResponce.rates.BMD),
            ExchangeRate("BND", currentRateResponce.rates.BND),
            ExchangeRate("BOB", currentRateResponce.rates.BOB),
            ExchangeRate("BRL", currentRateResponce.rates.BRL),
            ExchangeRate("BSD", currentRateResponce.rates.BSD),
            ExchangeRate("BTC", currentRateResponce.rates.BTC),
            ExchangeRate("BTN", currentRateResponce.rates.BTN),
            ExchangeRate("BWP", currentRateResponce.rates.BWP),
            ExchangeRate("BYN", currentRateResponce.rates.BYN),
            ExchangeRate("BZD", currentRateResponce.rates.BZD),
            ExchangeRate("CAD", currentRateResponce.rates.CAD),
            ExchangeRate("CDF", currentRateResponce.rates.CDF),
            ExchangeRate("CHF", currentRateResponce.rates.CHF),
            ExchangeRate("CLF", currentRateResponce.rates.CLF),
            ExchangeRate("CLP", currentRateResponce.rates.CLP),
            ExchangeRate("CNH", currentRateResponce.rates.CNH),
            ExchangeRate("CNY", currentRateResponce.rates.CNY),
            ExchangeRate("COP", currentRateResponce.rates.COP),
            ExchangeRate("CRC", currentRateResponce.rates.CRC),
            ExchangeRate("CUC", currentRateResponce.rates.CUC),
            ExchangeRate("CUP", currentRateResponce.rates.CUP),
            ExchangeRate("CVE", currentRateResponce.rates.CVE),
            ExchangeRate("CZK", currentRateResponce.rates.CZK),
            ExchangeRate("DJF", currentRateResponce.rates.DJF),
            ExchangeRate("DKK", currentRateResponce.rates.DKK),
            ExchangeRate("DOP", currentRateResponce.rates.DOP),
            ExchangeRate("DZD", currentRateResponce.rates.DZD),
            ExchangeRate("EGP", currentRateResponce.rates.EGP),
            ExchangeRate("ERN", currentRateResponce.rates.ERN),
            ExchangeRate("ETB", currentRateResponce.rates.ETB),
            ExchangeRate("EUR", currentRateResponce.rates.EUR),
            ExchangeRate("FJD", currentRateResponce.rates.FJD),
            ExchangeRate("FKP", currentRateResponce.rates.FKP),
            ExchangeRate("GBP", currentRateResponce.rates.GBP),
            ExchangeRate("GEL", currentRateResponce.rates.GEL),
            ExchangeRate("GGP", currentRateResponce.rates.GGP),
            ExchangeRate("GHS", currentRateResponce.rates.GHS),
            ExchangeRate("GIP", currentRateResponce.rates.GIP),
            ExchangeRate("GMD", currentRateResponce.rates.GMD),
            ExchangeRate("GNF", currentRateResponce.rates.GNF),
            ExchangeRate("GTQ", currentRateResponce.rates.GTQ),
            ExchangeRate("GYD", currentRateResponce.rates.GYD),
            ExchangeRate("HKD", currentRateResponce.rates.HKD),
            ExchangeRate("HNL", currentRateResponce.rates.HNL),
            ExchangeRate("HRK", currentRateResponce.rates.HRK),
            ExchangeRate("HTG", currentRateResponce.rates.HTG),
            ExchangeRate("HUF", currentRateResponce.rates.HUF),
            ExchangeRate("IDR", currentRateResponce.rates.IDR),
            ExchangeRate("ILS", currentRateResponce.rates.ILS),
            ExchangeRate("IMP", currentRateResponce.rates.IMP),
            ExchangeRate("INR", currentRateResponce.rates.INR),
            ExchangeRate("IQD", currentRateResponce.rates.IQD),
            ExchangeRate("IRR", currentRateResponce.rates.IRR),
            ExchangeRate("ISK", currentRateResponce.rates.ISK),
            ExchangeRate("JEP", currentRateResponce.rates.JEP),
            ExchangeRate("JMD", currentRateResponce.rates.JMD),
            ExchangeRate("JOD", currentRateResponce.rates.JOD),
            ExchangeRate("JPY", currentRateResponce.rates.JPY),
            ExchangeRate("KES", currentRateResponce.rates.KES),
            ExchangeRate("KGS", currentRateResponce.rates.KGS),
            ExchangeRate("KHR", currentRateResponce.rates.KHR),
            ExchangeRate("KMF", currentRateResponce.rates.KMF),
            ExchangeRate("KPW", currentRateResponce.rates.KPW),
            ExchangeRate("KRW", currentRateResponce.rates.KRW),
            ExchangeRate("KWD", currentRateResponce.rates.KWD),
            ExchangeRate("KYD", currentRateResponce.rates.KYD),
            ExchangeRate("KZT", currentRateResponce.rates.KZT),
            ExchangeRate("LAK", currentRateResponce.rates.LAK),
            ExchangeRate("LBP", currentRateResponce.rates.LBP),
            ExchangeRate("LKR", currentRateResponce.rates.LKR),
            ExchangeRate("LRD", currentRateResponce.rates.LRD),
            ExchangeRate("LSL", currentRateResponce.rates.LSL),
            ExchangeRate("LYD", currentRateResponce.rates.LYD),
            ExchangeRate("MAD", currentRateResponce.rates.MAD),
            ExchangeRate("MDL", currentRateResponce.rates.MDL),
            ExchangeRate("MGA", currentRateResponce.rates.MGA),
            ExchangeRate("MKD", currentRateResponce.rates.MKD),
            ExchangeRate("MMK", currentRateResponce.rates.MMK),
            ExchangeRate("MNT", currentRateResponce.rates.MNT),
            ExchangeRate("MOP", currentRateResponce.rates.MOP),
            ExchangeRate("MRU", currentRateResponce.rates.MRU),
            ExchangeRate("MUR", currentRateResponce.rates.MUR),
            ExchangeRate("MVR", currentRateResponce.rates.MVR),
            ExchangeRate("MWK", currentRateResponce.rates.MWK),
            ExchangeRate("MXN", currentRateResponce.rates.MXN),
            ExchangeRate("MYR", currentRateResponce.rates.MYR),
            ExchangeRate("MZN", currentRateResponce.rates.MZN),
            ExchangeRate("NAD", currentRateResponce.rates.NAD),
            ExchangeRate("NGN", currentRateResponce.rates.NGN),
            ExchangeRate("NIO", currentRateResponce.rates.NIO),
            ExchangeRate("NOK", currentRateResponce.rates.NOK),
            ExchangeRate("NPR", currentRateResponce.rates.NPR),
            ExchangeRate("NZD", currentRateResponce.rates.NZD),
            ExchangeRate("OMR", currentRateResponce.rates.OMR),
            ExchangeRate("PAB", currentRateResponce.rates.PAB),
            ExchangeRate("PEN", currentRateResponce.rates.PEN),
            ExchangeRate("PGK", currentRateResponce.rates.PGK),
            ExchangeRate("PHP", currentRateResponce.rates.PHP),
            ExchangeRate("PKR", currentRateResponce.rates.PKR),
            ExchangeRate("PLN", currentRateResponce.rates.PLN),
            ExchangeRate("PYG", currentRateResponce.rates.PYG),
            ExchangeRate("QAR", currentRateResponce.rates.QAR),
            ExchangeRate("RON", currentRateResponce.rates.RON),
            ExchangeRate("RSD", currentRateResponce.rates.RSD),
            ExchangeRate("RUB", currentRateResponce.rates.RUB),
            ExchangeRate("RWF", currentRateResponce.rates.RWF),
            ExchangeRate("SAR", currentRateResponce.rates.SAR),
            ExchangeRate("SBD", currentRateResponce.rates.SBD),
            ExchangeRate("SCR", currentRateResponce.rates.SCR),
            ExchangeRate("SDG", currentRateResponce.rates.SDG),
            ExchangeRate("SEK", currentRateResponce.rates.SEK),
            ExchangeRate("SGD", currentRateResponce.rates.SGD),
            ExchangeRate("SHP", currentRateResponce.rates.SHP),
            ExchangeRate("SLL", currentRateResponce.rates.SLL),
            ExchangeRate("SOS", currentRateResponce.rates.SOS),
            ExchangeRate("SRD", currentRateResponce.rates.SRD),
            ExchangeRate("SSP", currentRateResponce.rates.SSP),
            ExchangeRate("STD", currentRateResponce.rates.STD),
            ExchangeRate("STN", currentRateResponce.rates.STN),
            ExchangeRate("SVC", currentRateResponce.rates.SVC),
            ExchangeRate("SYP", currentRateResponce.rates.SYP),
            ExchangeRate("SZL", currentRateResponce.rates.SZL),
            ExchangeRate("THB", currentRateResponce.rates.THB),
            ExchangeRate("TJS", currentRateResponce.rates.TJS),
            ExchangeRate("TMT", currentRateResponce.rates.TMT),
            ExchangeRate("TND", currentRateResponce.rates.TND),
            ExchangeRate("TOP", currentRateResponce.rates.TOP),
            ExchangeRate("TRY", currentRateResponce.rates.TRY),
            ExchangeRate("TTD", currentRateResponce.rates.TTD),
            ExchangeRate("TWD", currentRateResponce.rates.TWD),
            ExchangeRate("TZS", currentRateResponce.rates.TZS),
            ExchangeRate("UAH", currentRateResponce.rates.UAH),
            ExchangeRate("UGX", currentRateResponce.rates.UGX),
            ExchangeRate("USD", currentRateResponce.rates.USD),
            ExchangeRate("UYU", currentRateResponce.rates.UYU),
            ExchangeRate("UZS", currentRateResponce.rates.UZS),
            ExchangeRate("VES", currentRateResponce.rates.VES),
            ExchangeRate("VND", currentRateResponce.rates.VND),
            ExchangeRate("VUV", currentRateResponce.rates.VUV),
            ExchangeRate("WST", currentRateResponce.rates.WST),
            ExchangeRate("XAF", currentRateResponce.rates.XAF),
            ExchangeRate("XAG", currentRateResponce.rates.XAG),
            ExchangeRate("XAU", currentRateResponce.rates.XAU),
            ExchangeRate("XCD", currentRateResponce.rates.XCD),
            ExchangeRate("XDR", currentRateResponce.rates.XDR),
            ExchangeRate("XOF", currentRateResponce.rates.XOF),
            ExchangeRate("XPD", currentRateResponce.rates.XPD),
            ExchangeRate("XPF", currentRateResponce.rates.XPF),
            ExchangeRate("XPT", currentRateResponce.rates.XPT),
            ExchangeRate("YER", currentRateResponce.rates.YER),
            ExchangeRate("ZAR", currentRateResponce.rates.ZAR),
            ExchangeRate("ZMW", currentRateResponce.rates.ZMW),
            ExchangeRate("ZWL", currentRateResponce.rates.ZWL)
        )
    }
}