package jsm.mdata.seguimiento.scraping;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jsm.mdata.seguimiento.dto.ProductoVarDTO;

public class Scraper
{

	/**
	 * 
	 */
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36";

	/**
	 * @param page
	 * @return
	 * @throws Throwable
	 */
	public static ProductoVarDTO getProductoFromMorningstar(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie",
				"RT_es_LANG=es-ES; OptanonAlertBoxClosed=2023-06-01T07:11:16.206Z; ad-profile=%7B%22NeedRefresh%22%3Afalse%2C%22UserType%22%3A0%2C%22AudienceType%22%3A-1%2C%22PortofolioCreated%22%3A0%2C%22IsForObsr%22%3Afalse%2C%22NeedPopupAudienceBackfill%22%3Afalse%2C%22EnableInvestmentInUK%22%3A-1%7D; __utmz=83116432.1695372806.4.3.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); ASP.NET_SessionId=3hpgaob11ixgcfuxwei1roh2; __utma=83116432.2070105484.1685603469.1695883445.1695972050.7; __utmc=83116432; __utmt=1; _gid=GA1.2.830028229.1695972052; _ga_8R1W3TJHY4=GS1.1.1695972049.7.1.1695972068.0.0.0; __utmb=83116432.2.10.1695972050; AWSALB=uB98VGKXT/DIZVptidvpgD73fUfdui4vYmZu5d1IqdUU3joVY8PW3DBvXGGLNRZivQ96goSmqxwdYfhhuASV/FG+qE5oW6r3eB47lpKaU1sNXuMiqXgXavcrADaE; AWSALBCORS=uB98VGKXT/DIZVptidvpgD73fUfdui4vYmZu5d1IqdUU3joVY8PW3DBvXGGLNRZivQ96goSmqxwdYfhhuASV/FG+qE5oW6r3eB47lpKaU1sNXuMiqXgXavcrADaE; _ga=GA1.2.2070105484.1685603469; OptanonConsent=isGpcEnabled=0&datestamp=Fri+Sep+29+2023+09%3A21%3A18+GMT%2B0200+(Central+European+Summer+Time)&version=6.23.0&isIABGlobal=false&hosts=&consentId=3c842d99-4a92-4064-9228-40209e2d40fd&interactionCount=1&landingPath=NotLandingPage&groups=C0001%3A1%2CC0003%3A1%2CC0002%3A1%2CC0004%3A1&geolocation=%3B&AwaitingReconsent=false");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Elements tablasDatos = page.getElementsByClass("snapshotTextColor snapshotTextFontStyle snapshotTable overviewKeyStatsTable");
		Element tablaDatos = tablasDatos.get(0);
		Elements filas = tablaDatos.getElementsByTag("tr");
		String fechaValor = null;
		String valorTitulo = null;
		String fechaTer = null;
		String ter = null;
		for (Element fila : filas)
		{
			if (fila.text().startsWith("VL "))
			{
				fechaValor = fila.text().substring(3, 13);
				valorTitulo = fila.text().substring(18, fila.text().length());
			}
			else if (fila.text().startsWith("Precio de Cierre "))
			{
				fechaValor = fila.text().substring(17, 27);
				valorTitulo = fila.text().substring(32, fila.text().length());
			}
			else if (fila.text().startsWith("Gastos Corrientes ") && !fila.text().contains("-%"))
			{
				fechaTer = fila.text().substring(18, 28);
				ter = fila.text().substring(29, fila.text().length() - 1);
			}
		}
		ProductoVarDTO productoVar = new ProductoVarDTO();
		productoVar.setProductoId(productoId);
		productoVar.setFechaValor(new SimpleDateFormat("dd/MM/yyyy").parse(fechaValor));
		productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(valorTitulo).doubleValue())));
		if (ter != null && fechaTer != null && !ter.trim().equalsIgnoreCase("") && !fechaTer.trim().equalsIgnoreCase(""))
		{
			productoVar.setFechaTer(new SimpleDateFormat("dd/MM/yyyy").parse(fechaTer));
			productoVar.setTer(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(ter).doubleValue())));
		}
		return productoVar;
	}

	/**
	 * @param page
	 * @return
	 * @throws Throwable
	 */
	public static ProductoVarDTO getProductoFromFinancialTimes(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie",
				"GZIP=1; spoor-id=clmpykvoo0000357hlskp6hru; permutive-id=1b352687-46af-4dc5-8076-3c5347af1d5f; _ga=GA1.2.504360035.1695106393; oda_uid=140600%3A%3A@@6509455a5eb251cb79fcf10a; oda_ug=140600%3A%3A@@Public; FTConsent=marketingBypost%3Aoff%2CmarketingByemail%3Aoff%2CmarketingByphonecall%3Aoff%2CmarketingByfax%3Aoff%2CmarketingBysms%3Aoff%2CenhancementBypost%3Aoff%2CenhancementByemail%3Aoff%2CenhancementByphonecall%3Aoff%2CenhancementByfax%3Aoff%2CenhancementBysms%3Aoff%2CbehaviouraladsOnsite%3Aon%2CdemographicadsOnsite%3Aon%2CrecommendedcontentOnsite%3Aon%2CprogrammaticadsOnsite%3Aon%2CcookiesUseraccept%3Aoff%2CcookiesOnsite%3Aoff%2CmembergetmemberByemail%3Aoff; FTCookieConsentGDPR=true; _gid=GA1.2.1579464534.1695888013; __gads=ID=64b1c3855fb52600:T=1695106394:RT=1695889298:S=ALNI_MZVqNzkh9lybwOYte3IUpVU57uRCQ; oda_lv=140600%3A%3A@@1695889317499; _ga_2DSMN2JH8F=GS1.2.1695888012.5.1.1695889329.0.0.0");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Elements listaElements = page.getElementsByClass("mod-ui-data-list__value");
		Element element = listaElements.get(0);
		String valorTitulo = element.text();
		ProductoVarDTO productoVar = new ProductoVarDTO();
		productoVar.setProductoId(productoId);
		productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(valorTitulo.replaceAll(",", ""))));
		return productoVar;
	}

	/**
	 * @param page
	 * @return
	 * @throws Throwable
	 */
	public static ProductoVarDTO getProductoFromInvesting(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie",
				"page_equity_viewed=0; adBlockerNewUserDomains=1684825274; udid=dba9811c3280618593852affdf73e7af; protectedMedia=2; OptanonAlertBoxClosed=2023-05-23T07:01:22.145Z; OTAdditionalConsentString=1~39.43.46.55.61.70.83.89.93.108.117.122.124.135.136.143.144.147.149.159.162.167.171.192.196.202.211.218.228.230.239.241.259.266.286.291.311.317.322.323.327.338.367.371.385.389.394.397.407.413.415.424.430.436.445.449.453.482.486.491.494.495.501.503.505.522.523.540.550.559.560.568.574.576.584.587.591.737.745.787.802.803.817.820.821.839.864.867.874.899.904.922.931.938.979.981.985.1003.1024.1027.1031.1033.1040.1046.1051.1053.1067.1085.1092.1095.1097.1099.1107.1127.1135.1143.1149.1152.1162.1166.1186.1188.1201.1205.1211.1215.1226.1227.1230.1252.1268.1270.1276.1284.1286.1290.1301.1307.1312.1345.1356.1364.1365.1375.1403.1415.1416.1419.1440.1442.1449.1455.1456.1465.1495.1512.1516.1525.1540.1548.1555.1558.1564.1570.1577.1579.1583.1584.1591.1603.1616.1638.1651.1653.1665.1667.1677.1678.1682.1697.1699.1703.1712.1716.1721.1725.1732.1745.1750.1765.1769.1782.1786.1800.1810.1825.1827.1832.1838.1840.1842.1843.1845.1859.1866.1870.1878.1880.1889.1899.1917.1929.1942.1944.1962.1963.1964.1967.1968.1969.1978.2003.2007.2008.2027.2035.2039.2044.2047.2052.2056.2064.2068.2070.2072.2074.2088.2090.2103.2107.2109.2115.2124.2130.2133.2135.2137.2140.2145.2147.2150.2156.2166.2177.2183.2186.2202.2205.2216.2219.2220.2222.2225.2234.2253.2264.2279.2282.2292.2299.2305.2309.2312.2316.2322.2325.2328.2331.2334.2335.2336.2337.2343.2354.2357.2358.2359.2370.2376.2377.2387.2392.2394.2400.2403.2405.2407.2411.2414.2416.2418.2425.2440.2447.2461.2462.2465.2468.2472.2477.2481.2484.2486.2488.2493.2497.2498.2499.2501.2510.2511.2517.2526.2527.2532.2534.2535.2542.2552.2563.2564.2567.2568.2569.2571.2572.2575.2577.2583.2584.2596.2601.2604.2605.2608.2609.2610.2612.2614.2621.2628.2629.2633.2634.2636.2642.2643.2645.2646.2647.2650.2651.2652.2656.2657.2658.2660.2661.2669.2670.2677.2681.2684.2686.2687.2690.2695.2698.2707.2713.2714.2729.2739.2767.2768.2770.2772.2784.2787.2791.2792.2798.2801.2805.2812.2813.2816.2817.2818.2821.2822.2827.2830.2831.2834.2838.2839.2840.2844.2846.2847.2849.2850.2852.2854.2856.2860.2862.2863.2865.2867.2869.2873.2874.2875.2876.2878.2880.2881.2882.2883.2884.2886.2887.2888.2889.2891.2893.2894.2895.2897.2898.2900.2901.2908.2909.2911.2912.2913.2914.2916.2917.2918.2919.2920.2922.2923.2924.2927.2929.2930.2931.2939.2940.2941.2947.2949.2950.2956.2958.2961.2963.2964.2965.2966.2968.2970.2973.2974.2975.2979.2980.2981.2983.2985.2986.2987.2991.2994.2995.2997.2999.3000.3002.3003.3005.3008.3009.3010.3012.3016.3017.3018.3019.3024.3025.3028.3034.3037.3038.3043.3045.3048.3052.3053.3055.3058.3059.3063.3065.3066.3068.3070.3072.3073.3074.3075.3076.3077.3078.3089.3090.3093.3094.3095.3097.3099.3104.3106.3109.3112.3117.3118.3119.3120.3124.3126.3127.3128.3130.3135.3136.3145.3149.3150.3151.3154.3155.3162.3163.3167.3172.3173.3180.3182.3183.3184.3185.3187.3188.3189.3190.3194.3196.3197.3209.3210.3211.3214.3215.3217.3219.3222.3223.3225.3226.3227.3228.3230.3231.3232.3234.3235.3236.3237.3238.3240.3244.3245.3250.3251.3253.3257.3260.3268.3270.3272.3281.3288.3290.3292.3293.3295.3296.3300.3306.3307.3308.3314.3315.3316.3318.3324.3327.3328.3330.3531.3831.3931; usprivacy=1YNN; _cc_id=cb181396c35ad7c65266b6d6f5b2acf5; _fbp=fb.1.1684825420074.513422014; im_sharedid=f82ec759-e5a2-454d-9897-a803cdb7221c; im_sharedid=f82ec759-e5a2-454d-9897-a803cdb7221c; _hjSessionUser_174945=eyJpZCI6IjUxZDg3ZmViLTdjNDctNWNjOC1iNjJkLTk1MmJiYzhmMDU2ZSIsImNyZWF0ZWQiOjE2ODcxNTg3OTQ3MjEsImV4aXN0aW5nIjp0cnVlfQ==; r_p_s_n=1; pms={\"f\":2,\"s\":2}; _imhb_exp_udid=b; leaderboard_variant=0; pm_score=clear; _lr_env_src_ats=false; pbjs-unifiedid=%7B%22TDID_LOOKUP%22%3A%22FALSE%22%2C%22TDID_CREATED_AT%22%3A%222023-09-12T07%3A54%3A46%22%7D; _ga_9XQG87E8PF=GS1.2.1694505293.2.0.1694505293.0.0.0; eupubconsent-v2=CPsNvrAPsNvrAAcABBENDYCsAP_AAH_AAChQJbtf_X__b2_r-_5_f_t0eY1P9_7__-0zjhfdF-8N3f_X_L8X52M5vF16tqoKuR4ku3LBIUdlHPHcTVmw6okVryPsbk2cr7NKJ7PEmlMbM2dYGH97n1_z-ZKY7___f__z_v-v___9____7-3f3__5__--__e_V_-9zfn9_____9vP___9v-_9_3________3_r9_7_D9-f_84JbgEmGhcQBdkSMBNtGEUCAEYVhIVQKACiACFogMIHVwU7K4CfWACAEAKABwIAQwAoyABAAAJAEhEAEgRwIBAABAIAAQAKhAIAGNgAFgBYCAQACgOhYoxQBCBIQZEBEQpgQBSJBQT2VCCUH6gphCGWWAFBo_4qEBCsgYrAiEhYOQ4AkBLxJIHmKN8ABCAFAKIUKxFJ6YAhQTIAAAA.f_gAD_gAAAAA; ov_page_variant3=1; invpro_promote_variant=-1; ses_id=ZCozcmZpMjowdG5oNWQyMGIyMW40OmZtZW00Mjs%2Bb3lkcDA%2BM2QwdjU6aCZvbDIuNj4zOzBvMDY9aDU8ZzNuPmQ3MzVmOTJpMGNuZDUzMjRiNjE%2FNDRmMWUxNDI7OW9sZDEwNDMxMD01MWgxb2UyNDYkMy8wdDAhPW81ZWcmbilkazNyZjYyazBnbmA1bjI4YjUxbzQ2ZmVlNzRkOz9vd2Qv; SideBlockUser=a%3A2%3A%7Bs%3A6%3A%22stacks%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Ba%3A8%3A%7Bi%3A0%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bi%3A48358%3Bs%3A10%3A%22pair_title%22%3Bs%3A7%3A%22XAU%2FEUR%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A19%3A%22%2Fcurrencies%2Fxau-eur%22%3B%7Di%3A1%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bi%3A68%3Bs%3A10%3A%22pair_title%22%3Bs%3A7%3A%22XAU%2FUSD%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A19%3A%22%2Fcurrencies%2Fxau-usd%22%3B%7Di%3A2%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bi%3A49800%3Bs%3A10%3A%22pair_title%22%3Bs%3A7%3A%22BTC%2FEUR%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A23%3A%22%2Fcrypto%2Fbitcoin%2Fbtc-eur%22%3B%7Di%3A3%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bi%3A1057391%3Bs%3A10%3A%22pair_title%22%3Bs%3A7%3A%22BTC%2FUSD%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A30%3A%22%2Findices%2Finvesting.com-btc-usd%22%3B%7Di%3A4%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bi%3A46882%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A43%3A%22%2Fetfs%2Fetfs-physical-swiss-gold-uk%3Fcid%3D46882%22%3B%7Di%3A5%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A7%3A%221084955%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A28%3A%22%2Ffunds%2Famundi-msci-wrld-ae-c%22%3B%7Di%3A6%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A7%3A%221121195%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A35%3A%22%2Ffunds%2Fesfera-i-baelo-patrimonio-fi%22%3B%7Di%3A7%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A7%3A%221067215%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A39%3A%22%2Ffunds%2Findexa-mas-rentabilidad-bonos-pp%22%3B%7D%7D%7Ds%3A10%3A%22stack_size%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Bi%3A8%3B%7D%7D; user-browser-sessions=21; upa=eyJpbnZfcHJvX2Z1bm5lbCI6IjEiLCJtYWluX2FjIjoiMTAiLCJtYWluX3NlZ21lbnQiOiIyIiwiZGlzcGxheV9yZm0iOiIxMTEiLCJhZmZpbml0eV9zY29yZV9hY19lcXVpdGllcyI6IjQiLCJhZmZpbml0eV9zY29yZV9hY19jcnlwdG9jdXJyZW5jaWVzIjoiOCIsImFmZmluaXR5X3Njb3JlX2FjX2N1cnJlbmNpZXMiOiI1IiwiYWN0aXZlX29uX2lvc19hcHAiOiIwIiwiYWN0aXZlX29uX2FuZHJvaWRfYXBwIjoiMCIsImFjdGl2ZV9vbl93ZWIiOiIxIiwiaW52X3Byb191c2VyX3Njb3JlIjoiMTAwIn0%3D; _gid=GA1.2.380141650.1695887783; _imntz_error=0; cto_bundle=2wD8El9BUWVjSk82ZTJWZCUyRjYlMkZlNmxwdGVEY3R2NDlsYW5WSGtNRmRGRW1WUjRWVXJESkZoOUwycXlOaXdueGVaRERDZHB2MTY0MlJZdEZRUTRMTVo2Ylc4TXpXZDhDcXpXWlJHUE1qWkt6NGFWbVd2biUyRm9wTk8lMkZxWjRnb0lHN2FNUjZZelRBU0VGNFJSZjZrN2VxNXpMdEN6alRROTFGd1BtcjVLNnc4eEU1TUpCMCUzRA; connectId={\"lastUsed\":1695887786117,\"lastSynced\":1695887786117}; panoramaId_expiry=1695974186130; panoramaId=904310328c866eb0d486a240cf3ea9fb927aee766bdee10e577c1f21aa9c7ad8; pbjs-unifiedid_last=Thu%2C%2028%20Sep%202023%2009%3A57%3A32%20GMT; lifetime_page_view_count=89; OptanonConsent=isGpcEnabled=0&datestamp=Thu+Sep+28+2023+11%3A57%3A35+GMT%2B0200+(Central+European+Summer+Time)&version=202303.1.0&browserGpcFlag=0&isIABGlobal=false&hosts=&consentId=2d303468-6269-44a2-a8ef-dd00eb74f8c6&interactionCount=1&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1%2CC0004%3A1%2CSTACK42%3A1&geolocation=ES%3BMD&AwaitingReconsent=false; _pbjs_userid_consent_data=2375751010770540; __gads=ID=44203946862b560b:T=1684825381:RT=1695895832:S=ALNI_Mb_rGAasYBGEqbwMmUga1uRnJmypw; __gpi=UID=00000c33767ad8d0:T=1684825381:RT=1695895832:S=ALNI_MY4RLG2VNQOzwgYWbMJ2p6zZU8bfQ; _ga=GA1.2.894918970.1684825276; _ga_C4NDLGKVMK=GS1.1.1695897778.24.0.1695897780.58.0.0");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Elements listaElements = page.getElementsByClass("text-5xl/9 font-bold md:text-[42px] md:leading-[60px] text-[#232526]");
		Element element = listaElements.get(0);
		String valorTitulo = element.text();
		ProductoVarDTO productoVar = new ProductoVarDTO();
		productoVar.setProductoId(productoId);
		productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(valorTitulo).doubleValue())));
		return productoVar;
	}

	/**
	 * @param page
	 * @return
	 * @throws Throwable
	 */
	public static ProductoVarDTO getProductoFromYahoo(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie",
				"F=d=.A_ZHvQ9vPCZ4y.HipEny9MpG9o3ucSi2pEvhg--; PH=l=es-ES; Y=v=1&n=07uhrfhdk740o&l=9ik180ic/o&p=m2c09oh00000000&r=n5&intl=es; B=0jqmsidi6op8n&b=4&d=0SP2cAZtYFo.r7EqIN6a&s=st&i=n3qFETx7RjI44lFnZ8Be; GUC=AQAACAFlH6BlTUIfaQTT&s=AQAAACCZFS33&g=ZR5aVQ; A1=d=AQABBERsFmUCEFJNe6b15ZefjGsl-PmZaFIFEgAACAGgH2VNZeS2b2UB9qMAAAcIsmsWZaJRP3MID3sAeRCaATU418Wt1agqtgkBBwoB8A&S=AQAAAuPCTL1wznApSMi_PJKXDrU; A3=d=AQABBERsFmUCEFJNe6b15ZefjGsl-PmZaFIFEgAACAGgH2VNZeS2b2UB9qMAAAcIsmsWZaJRP3MID3sAeRCaATU418Wt1agqtgkBBwoB8A&S=AQAAAuPCTL1wznApSMi_PJKXDrU; gam_id=y-3avXiD1G2uIuvSCD9ARcBP5_6COO9P8gmVYYyXXpmGYvRQj1CQ---A; axids=gam=y-3avXiD1G2uIuvSCD9ARcBP5_6COO9P8gmVYYyXXpmGYvRQj1CQ---A&dv360=eS1fYUZST05WRTJ1SG1rTW83VW1tRlEwOTcyVkxUNklHNnh3MDZFSnhZZVlVVjA2NWdQSDZHMXU5d0trcTVPS3dTYlBzY35B; tbla_id=db0985a5-a6e0-46ce-a6cf-bd7b50e6db5a-tuctb65ea9e; PRF=t%3DBTC-EUR%252BCSSPX.SW%252BIE00B5BMR087.IR%252BEUR%253DX%252BGC%253DF%252BEURUSD%253DX%252BORO-USD%252BEXX6.DE%252BGB00BJYDH287.SG%252B0P0001CTGR.F%252B0P00012PP6.F%252BMEUD.PA%252B0P0000IKFQ.F%252B0P0000IKFR.F%252BJE00B588CD74.SG; ucs=tr=1697791593000; A1S=d=AQABBERsFmUCEFJNe6b15ZefjGsl-PmZaFIFEgAACAGgH2VNZeS2b2UB9qMAAAcIsmsWZaJRP3MID3sAeRCaATU418Wt1agqtgkBBwoB8A&S=AQAAAuPCTL1wznApSMi_PJKXDrU; OTH=v=2&s=2&d=eyJraWQiOiIwMTY0MGY5MDNhMjRlMWMxZjA5N2ViZGEyZDA5YjE5NmM5ZGUzZWQ5IiwiYWxnIjoiUlMyNTYifQ.eyJjdSI6eyJndWlkIjoiWFZKVEgyV1E3RVRZM0lHUE5VUVBZNVhSR0kiLCJwZXJzaXN0ZW50Ijp0cnVlLCJzaWQiOiJuMW84R1RZbk4ydjkifX0.g-QgUfoMo1J6s8iCATb-T_Tni6bdvtqN3PaIzfsZiFGazgbDrGF5nThuLNlVdBksDyfkWCxcOBY2QP52-nQ80opvdspqjPL2GflOr9mfdsx3V83h9_gycRcPR6ALaDTZQEROxM2i0BDjh2k_aizg1lQDCljxaYzVaoUNCSPSVgs; T=af=QXpBQ1I1UjYmdHM9MTY5NzcwNTE5MyZwcz1SMTdiMzdtd0VmN09FUWwuVHZMajFRLS0-&d=bnMBeWFob28BZwFYVkpUSDJXUTdFVFkzSUdQTlVRUFk1WFJHSQFhYwFBRXYyNXpEWAFhbAFqc3ViaWFzbQFzYwFkZXNrdG9wX3dlYgFmcwE4aUczSUpka2JHVVgBenoBcHpPTWxCQTdFAWEBUUFFAWxhdAFRaEMua0IBbnUBMA--&kt=EAAxio.hdxbOT1QPJf8qu7ocw--~I&ku=FAAXwoVqdwfNo20y1whICp3WQzGxmq20DU4.9HXwxpVnnRg4huSxZvrDQhcpk0ph1ryzewujNTmOAoGvwbv7glwq51wCoYR3CEKwHd5_J1lkpXv2wRernavbUw_ineYn0A7yAVAyY4Kcbb20pgBhuhL.PcspdSi326ALeDksy7D8Gs-~E; cmp=t=1697705195&j=1&u=1---&v=99; EuConsent=CPxrYoAPxrYoAAOACBESDbCoAP_AAEfAACiQgoNB9G7WTXNncXp_YPs0eYUX1VBp4uAxBgCBA-ABzBsUIIwGVmEzJEyIJigCGAIAoGJBIEFtGAlAQFAQYIAFABHICEEAJBAAIGAAECAAAgBACBBIEwAAAAAQoUBXMhQgkAdEQFoIQchAlgAgAQIAICAEoIhBAgQAEAAgQABICEAIgigAggAAAAIAAAAEAFAIEQBABgECB____eAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQTFABINSogCbAgJCaQMIoEQIgqCACgUAAAAECBAAAmDAoQRgEqMBkAIEQABAAAAABQEACAAACABCAAIAggQAAACAQAAAAQCAAAEAAAAAAAAAAAAAQAgAAAAAAQgCIQAhBAACAACAAgoAAIABAAAAAAEAIARCAAAAAAABAAAAAAIAQBAABAAAAAAQAAAAAAAAQIACADAAADgkbLUAMNABgACIKAiADAAEQUBUAGAAIgoA; __gpi=UID=00000cbb8f817dc0:T=1697705197:RT=1697705197:S=ALNI_MYwrSCHX8uZWCMNQJcOrUGdYlpVng");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Elements listaElements = page.getElementsByClass("Fw(b) Fz(36px) Mb(-4px) D(ib)");
		Element element = listaElements.get(0);
		String valorTitulo = element.text();
		ProductoVarDTO productoVar = new ProductoVarDTO();
		productoVar.setProductoId(productoId);
		productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(valorTitulo).doubleValue())));
		return productoVar;
	}

	/**
	 * @param page
	 * @return
	 * @throws Throwable
	 */
	public static ProductoVarDTO getProductoFromQuefondos(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie", "JSESSIONID=C1EC7337DD048F5BC0BEDDACE89E3B65; _gid=GA1.2.2059476911.1695973321; __gads=ID=ef2a79d53f6f7021:T=1695716640:RT=1695973321:S=ALNI_MbPqxPfaz4cpiTD9fJwRl6xq2YjOA; __gpi=UID=00000cb033cba73a:T=1695716640:RT=1695973321:S=ALNI_MbGgQlJRaxZCobLU8aX48_F903Jbw; cookiesDisclosureCount=3; _ga_C37K8VSLPT=GS1.1.1695973320.2.1.1695973375.5.0.0; _ga=GA1.2.1182491834.1695716639");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Elements listaElements = page.getElementsByClass("w100");
		Element element = null;
		for (Element e : listaElements)
		{
			if (e.text().contains("Valor liquidativo"))
			{
				element = e;
				break;
			}
		}
		element = element.getElementsByClass("floatright").get(0);
		String valorTitulo = element.text().substring(0, element.text().indexOf(" "));
		ProductoVarDTO productoVar = new ProductoVarDTO();
		productoVar.setProductoId(productoId);
		productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(valorTitulo).doubleValue())));
		return productoVar;
	}

	/**
	 * @param page
	 * @return
	 * @throws Throwable
	 */
	public static ProductoVarDTO getProductoFromFinect(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie", "_gid=GA1.2.784602577.1695887710; _gcl_au=1.1.384570495.1695887721; g_state={\"i_p\":1695895527071,\"i_l\":1}; _gat_UA-34765805-2=1; __gads=ID=fefef5fa6516b8b7:T=1695887720:RT=1695974137:S=ALNI_MbQ91CywjgyViq_RQPPup1Alx7D3g; __gpi=UID=00000cb203015c42:T=1695887720:RT=1695974137:S=ALNI_MYiwBuSg89YQ3MgE4IFeAEoCCQ0RQ; _ga=GA1.1.588997350.1695716653; _ga_F5VP0V0660=GS1.1.1695974137.3.1.1695974152.45.0.0");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Element element = page.getElementsByClass("Label-sc-1esfagn-0 kDrrNc").get(0);
		String valorTitulo = element.text().substring(0, element.text().length() - 1);
		ProductoVarDTO productoVar = new ProductoVarDTO();
		productoVar.setProductoId(productoId);
		productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(valorTitulo).doubleValue())));
		return productoVar;
	}

	/**
	 * @param page
	 * @return
	 * @throws Throwable
	 */
	public static ProductoVarDTO getProductoFromDracma(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie", "d964a68d0298312fa3ac703176dc5e2b=df110815a07aca964f057dac8f5ebb4f");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Element element = page.getElementsByClass("cotizacion").get(0);
		String valorTitulo = element.text().substring(0, element.text().indexOf("€"));
		ProductoVarDTO productoVar = new ProductoVarDTO();
		productoVar.setProductoId(productoId);
		productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(valorTitulo).doubleValue())));
		return productoVar;
	}

	/**
	 * @param page
	 * @return
	 * @throws Throwable
	 */
	public static ProductoVarDTO getProductoFromAndorrano(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie", "efb5b5f7b1269de176cb2d2cb88303ba=e6d45d6a58c6bc5db82169c0eacd17c7; _clck=av0p5g|2|fff|0|1367; _clsk=r0iix4|1695984102257|1|1|p.clarity.ms/collect");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Element element = page.getElementsByClass("precio").get(0);
		String valorTitulo = element.text().substring(0, element.text().indexOf("€"));
		ProductoVarDTO productoVar = new ProductoVarDTO();
		productoVar.setProductoId(productoId);
		productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(valorTitulo).doubleValue())));
		return productoVar;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			ProductoVarDTO productoVar = null;

//			productoVar = getProductoFromMorningstar("TEST", "https://www.morningstar.es/es/etf/snapshot/snapshot.aspx?id=0P00001NR4");
//			System.out.println(productoVar.getValorTitulo());
//
//			productoVar = getProductoFromFinancialTimes("TEST", "https://markets.ft.com/data/etfs/tearsheet/summary?s=EXX6:GER:EUR");
//			System.out.println(productoVar.getValorTitulo());
//
//			productoVar = getProductoFromInvesting("TEST", "https://es.investing.com/etfs/ishares-eb.rexx-gov-germany-10,5");
//			System.out.println(productoVar.getValorTitulo());
//
			productoVar = getProductoFromYahoo("TEST", "https://es.finance.yahoo.com/quote/BTC-EUR");
			System.out.println(productoVar.getValorTitulo());
//
//			productoVar = getProductoFromQuefondos("TEST", "https://www.quefondos.com/es/planes/ficha/index.html?isin=N5137");
//			System.out.println(productoVar.getValorTitulo());
//
//			productoVar = getProductoFromFinect("TEST", "https://www.finect.com/planes-pensiones/N5137-Indexa_mas_rentabilidad_bonos_pp");
//			System.out.println(productoVar.getValorTitulo());
//
//			productoVar = getProductoFromDracma("TEST", "https://www.dracmametales.com/precio-del-oro");
//			System.out.println(productoVar.getValorTitulo());
//
//			productoVar = getProductoFromAndorrano("TEST", "https://www.andorrano-joyeria.com/precio-del-oro");
//			System.out.println(productoVar.getValorTitulo());

		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
