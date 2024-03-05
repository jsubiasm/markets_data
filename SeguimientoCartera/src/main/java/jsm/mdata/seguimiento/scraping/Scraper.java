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
	public static ProductoVarDTO getProductoFromYahoo(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie",
				"PH=l=es-ES; B=0jqmsidi6op8n&b=4&d=0SP2cAZtYFo.r7EqIN6a&s=st&i=n3qFETx7RjI44lFnZ8Be; PRF=t%3DBTC-EUR%252BDE000A28M8D0.SG%252BVBTC.SW%252BDE000A28M8D0.AS%252BCSSPX.MI%252BCSSPX.SW%252BIE00B5BMR087.IR%252BEUR%253DX%252BGC%253DF%252BEURUSD%253DX%252BORO-USD%252BEXX6.DE%252BGB00BJYDH287.SG%252B0P0001CTGR.F%252B0P00012PP6.F%26newChartbetateaser%3D0%252C1698997286971; F=d=bVdoh4A9ufAGPvi.9nxz350YwajJk2t3LIUSyvKmQtF07mfYzFiCbrPYqpmqZAUolSdsrX9PRUfYmRi3Sj08hPTcVauA9Xrf.0tunDJSuJI76DB.EmLbhk0Am_hsTP6vlA--; Y=v=1&n=07uhrfhdk740o&l=9ik180ic/o&p=m2c09oh00000000&r=n5&intl=es; gam_id=y-3avXiD1G2uIuvSCD9ARcBP5_6COO9P8gmVYYyXXpmGYvRQj1CQ---A; tbla_id=db0985a5-a6e0-46ce-a6cf-bd7b50e6db5a-tuctb65ea9e; GUC=AQAACAFlzchl_EIfaQTT&s=AQAAAB69LwQT&g=ZcyEAw; A3=d=AQABBERsFmUCEFJNe6b15ZefjGsl-PmZaFIFEgAACAHIzWX8ZeRF0iMA9qMAAAcIsmsWZaJRP3MIDyqF-SDasMUcyLcOT_wvhQkBBwoB2g&S=AQAAAq0ggixCjGFVuZsMv9Fpry8; A1=d=AQABBERsFmUCEFJNe6b15ZefjGsl-PmZaFIFEgAACAHIzWX8ZeRF0iMA9qMAAAcIsmsWZaJRP3MIDyqF-SDasMUcyLcOT_wvhQkBBwoB2g&S=AQAAAq0ggixCjGFVuZsMv9Fpry8; axids=gam=y-3avXiD1G2uIuvSCD9ARcBP5_6COO9P8gmVYYyXXpmGYvRQj1CQ---A&dv360=eS1fYUZST05WRTJ1SG1rTW83VW1tRlEwOTcyVkxUNklHNnh3MDZFSnhZZVlVVjA2NWdQSDZHMXU5d0trcTVPS3dTYlBzY35B&ydsp=y-NXoMIPJE2uL4umONUtq4yENWXcENey2ItYCAmZooodYXKQLHXAi3vnM.HwEFOR6Wskq4~A&tbla=y-pJ63Nz9G2uL6DgvlLrTWrEivNNBIjk83fVMs_pQelBAjqqi5Xw---A; cmp=t=1709112008&j=1&u=1---&v=15; ucs=tr=1709198462000; OTH=v=2&s=2&d=eyJraWQiOiIwMTY0MGY5MDNhMjRlMWMxZjA5N2ViZGEyZDA5YjE5NmM5ZGUzZWQ5IiwiYWxnIjoiUlMyNTYifQ.eyJjdSI6eyJndWlkIjoiWFZKVEgyV1E3RVRZM0lHUE5VUVBZNVhSR0kiLCJwZXJzaXN0ZW50Ijp0cnVlLCJzaWQiOiJxQ1YxQnNXVDAwc0MifX0.wWf5EJqZiMJA8py_JlNZO18n8HtQikApvhqHo9cyblmrNOpW0DMrJfDrtvSslCAiWH-6YuTbi4hqcSqwoHAWssiwRYZbGOmQqqZMvOokkWm8K8_VAIwxt5Cuc8jiG_x9s4qOi40nkZB5yx9j3AEXyckfW3q7PkXn6XbwTyBRxLU; T=af=QXpBQ1I1UjYmdHM9MTcwOTExMjA2MiZwcz1ZaV9oMTNQdUI4UzlsOVFxTjdkQlVnLS0-&d=bnMBeWFob28BZwFYVkpUSDJXUTdFVFkzSUdQTlVRUFk1WFJHSQFhYwFBUE1ENlRVOAFhbAFqc3ViaWFzbQFzYwFkZXNrdG9wX3dlYgFmcwE4aUczSUpka2JHVVgBenoBLnJ2M2xCQTdFAWEBUUFFAWxhdAFrVFBubEIBbnUBMA--&kt=EAALnTPnnOLDQilYXvyC5GD3Q--~I&ku=FAAGMKGGjbsv370aRQJJQv5lnavn1Zk_2IS37VsHr6MaJFer8nH4yv6HCdbdT.q.0PyKDBqUQFTiLbzXruwG_Ao4.c_QtTotv0eNrWP_sikfNYc2BLXEM0.GAvNs7sQQbyht49Sx7BOj8V3uyIGkuTIktHInBHzW3YRFwBwkYJ6JDw-~E; __gpi=UID=00000d33572da16a:T=1709113774:RT=1709113774:S=ALNI_MbPF8p4s5QVpaH7LIN3l9kCeylRvg; __eoi=ID=36674165cb1429fe:T=1709113774:RT=1709113774:S=AA-Afja55KUx461swklhJoO9lkzO");
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
				"page_equity_viewed=0; adBlockerNewUserDomains=1684825274; udid=dba9811c3280618593852affdf73e7af; protectedMedia=2; usprivacy=1YNN; _cc_id=cb181396c35ad7c65266b6d6f5b2acf5; _fbp=fb.1.1684825420074.513422014; im_sharedid=f82ec759-e5a2-454d-9897-a803cdb7221c; _hjSessionUser_174945=eyJpZCI6IjUxZDg3ZmViLTdjNDctNWNjOC1iNjJkLTk1MmJiYzhmMDU2ZSIsImNyZWF0ZWQiOjE2ODcxNTg3OTQ3MjEsImV4aXN0aW5nIjp0cnVlfQ==; leaderboard_variant=0; ov_page_variant3=1; invpro_promote_variant=-1; ab_test_global=0; fairvalue_variant=0; OptanonAlertBoxClosed=2023-12-04T07:41:02.753Z; eupubconsent-v2=CP2QlRgP2QlRgAcABBENAdEsAP_gAEPgAChQg1NX_H__bW9r8Xr3aft0eY1P99j77uQxBhfJE-4FyLvW_JwXx2EwNA26tqIKmRIEu3ZBIQFlHJHURVigaogVryHsYkGchTNKJ6BkgFMRI2dYCF5vmYtj-QKY5_p_d3fx2D-t_dv83dzzz81Hn3f5f2ckcKCdQ58tDfn9bRKb-5IO9-78v4v09l_rk2_eTVn_pcvr7B-uft87_XU-9_fAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEQagCzDQqIA-yJCQi0DCKBACIKwgIoFAAAAJA0QEAJAwKdgYBLrCRACBFAAMEAIAAUZAAgAAEgAQiACQAoEAAEAgEAAAAAAgEADAwADgAtBAIAAQHQMUwoAFAsIEiMiIUwIQoEggJbKBBICwQVwgCLLAigERMFAAgCQAVgAAAsVgMASAlYkECWUG0AABAAgFFKFQik-MAQwJmy1U4om0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAAACAA.f_wACHwAAAAA; OTAdditionalConsentString=1~43.46.55.61.70.83.89.93.108.117.122.124.135.136.143.144.147.149.159.192.196.202.211.228.230.239.259.266.286.291.311.317.320.322.323.327.338.367.371.385.394.397.407.413.415.424.430.436.445.453.482.486.491.494.495.522.523.540.550.559.560.568.574.576.584.587.591.737.802.803.820.821.839.864.899.904.922.931.938.979.981.985.1003.1027.1031.1040.1046.1051.1053.1067.1085.1092.1095.1097.1099.1107.1135.1143.1149.1152.1162.1166.1186.1188.1201.1205.1215.1226.1227.1230.1252.1268.1270.1276.1284.1290.1301.1307.1312.1345.1356.1364.1365.1375.1403.1415.1416.1421.1440.1449.1455.1495.1512.1516.1525.1540.1548.1555.1558.1570.1577.1579.1583.1584.1591.1603.1616.1638.1651.1653.1667.1677.1678.1682.1697.1699.1703.1712.1716.1721.1725.1732.1745.1750.1765.1769.1782.1786.1800.1810.1825.1827.1832.1838.1840.1842.1843.1845.1859.1866.1870.1878.1880.1889.1899.1917.1929.1942.1944.1962.1963.1964.1967.1968.1969.1978.2003.2007.2008.2027.2035.2039.2047.2052.2056.2064.2068.2072.2074.2088.2090.2103.2107.2109.2115.2124.2130.2133.2135.2137.2140.2145.2147.2150.2156.2166.2177.2183.2186.2205.2216.2219.2220.2222.2225.2234.2253.2279.2282.2292.2299.2305.2309.2312.2316.2322.2325.2328.2331.2334.2335.2336.2337.2343.2354.2357.2358.2359.2370.2376.2377.2387.2392.2400.2403.2405.2407.2411.2414.2416.2418.2425.2440.2447.2461.2462.2465.2468.2472.2477.2481.2484.2486.2488.2493.2498.2499.2501.2510.2517.2526.2527.2532.2535.2542.2552.2563.2564.2567.2568.2569.2571.2572.2575.2577.2583.2584.2596.2604.2605.2608.2609.2610.2612.2614.2621.2628.2629.2633.2636.2642.2643.2645.2646.2650.2651.2652.2656.2657.2658.2660.2661.2669.2670.2677.2681.2684.2687.2690.2695.2698.2713.2714.2729.2739.2767.2768.2770.2772.2784.2787.2791.2792.2798.2801.2805.2812.2813.2816.2817.2821.2822.2827.2830.2831.2834.2838.2839.2844.2846.2849.2850.2852.2854.2860.2862.2863.2865.2867.2869.2873.2874.2875.2876.2878.2880.2881.2882.2883.2884.2886.2887.2888.2889.2891.2893.2894.2895.2897.2898.2900.2901.2908.2909.2913.2914.2916.2917.2918.2919.2920.2922.2923.2927.2929.2930.2931.2940.2941.2947.2949.2950.2956.2958.2961.2963.2964.2965.2966.2968.2973.2975.2979.2980.2981.2983.2985.2986.2987.2994.2995.2997.2999.3000.3002.3003.3005.3008.3009.3010.3012.3016.3017.3018.3019.3024.3025.3028.3034.3037.3038.3043.3048.3052.3053.3055.3058.3059.3063.3066.3068.3070.3073.3074.3075.3076.3077.3078.3089.3090.3093.3094.3095.3097.3099.3104.3106.3109.3112.3117.3119.3126.3127.3128.3130.3135.3136.3145.3150.3151.3154.3155.3163.3167.3172.3173.3182.3183.3184.3185.3187.3188.3189.3190.3194.3196.3209.3210.3211.3214.3215.3217.3219.3222.3223.3225.3226.3227.3228.3230.3231.3234.3235.3236.3237.3238.3240.3244.3245.3250.3251.3253.3257.3260.3268.3270.3272.3281.3288.3290.3292.3293.3295.3296.3299.3300.3306.3307.3314.3315.3316.3318.3324.3327.3328.3330.3331.3531.3731.3831.3931.4131.4531.4631.4731.4831.5031.5231.6931.7031.7235.7831.7931.8931.9731.10231.10631.10831.11031.11531.12831.13632.13731.14237.16831; takeover_variant=1; _au_1d=AU1D-0100-001704450014-SFLBXXC9-6QSI; pbjs-unifiedid=%7B%22TDID_LOOKUP%22%3A%22FALSE%22%2C%22TDID_CREATED_AT%22%3A%222024-01-05T10%3A21%3A04%22%7D; propicks_popup_counter=1; propicks_popup_did_user_dismissed=1; pbjs-unifiedid_last=Mon%2C%2008%20Jan%202024%2008%3A12%3A04%20GMT; _ga_C4NDLGKVMK=deleted; _ga_C4NDLGKVMK=deleted; pm_score=clear; __eventn_id=3b297172-9979-4c2e-ba2b-6f39a5daad6f; __eventn_uid=201499952; __eventn_id_usr=%7B%22adFreeUser%22%3A0%2C%22investingProUser%22%3A0%2C%22investingProPremiumUser%22%3A0%7D; ses_id=NnhhIGRrMzs%2Bem1rZTQwMmMzZTo3OWdsNT1nYTs%2BZ3E0IDc5YjU1czE%2BPXNubWV5YGNiNjdmZTRnbW5jNmdjZzZnYTpkNzNrPm5taGU%2FMDVjZGU7N2RnNzU9ZzY7OGdlNDM3M2JjNTYxMj1kbmZlOmByYn43c2V0ZzVuPjZ3YyQ2OWEgZDQzaj47bTJlPzAzY2FlbDdlZ2U1ZmdlO25nfzR%2F; OptanonConsent=isGpcEnabled=0&datestamp=Wed+Feb+14+2024+11%3A12%3A05+GMT%2B0100+(Central+European+Standard+Time)&version=202310.2.0&browserGpcFlag=0&isIABGlobal=false&hosts=&consentId=2d303468-6269-44a2-a8ef-dd00eb74f8c6&interactionCount=2&landingPath=NotLandingPage&groups=C0002%3A1%2CC0003%3A1%2CC0004%3A1%2CC0001%3A1%2CV2STACK42%3A1&geolocation=ES%3BMD&AwaitingReconsent=false; _pbjs_userid_consent_data=2233911542039943; _au_last_seen_iab_tcf=1709191611323; _ga_9XQG87E8PF=GS1.2.1709191642.11.0.1709191642.0.0.0; _ga_FVWZ0RM4DH=GS1.1.1709191615.1.0.1709191724.60.0.0; accessToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDk2Mjc0MTcsImp0aSI6IjIwMTQ5OTk1MiIsImlhdCI6MTcwOTYyMzgxNywiaXNzIjoiaW52ZXN0aW5nLmNvbSIsInVzZXJfaWQiOjIwMTQ5OTk1MiwicHJpbWFyeV9kb21haW5faWQiOiI0IiwiQXV0aG5TeXN0ZW1Ub2tlbiI6IiIsIkF1dGhuU2Vzc2lvblRva2VuIjoiIiwiRGV2aWNlVG9rZW4iOiIiLCJVYXBpVG9rZW4iOiJObmhoSUdSck16cyUyQmVtMXJaVFF3TW1NelpUbzNPV2RzTlQxbllUcyUyQlozRTBJRGM1WWpVMWN6RSUyQlBYTnViV1Y1WUdOaU5qZG1aVFJuYlc1ak5tZGpaelpuWVRwa056TnJQbTV0YUdVJTJGTURWalpHVTdOMlJuTnpVOVp6WTdPR2RsTkRNM00ySmpOVFl4TWoxa2JtWmxPbUJ5WW40M2MyVjBaelZ1UGpaM1l5UTJPV0VnWkRRemFqNDdiVEpsUHpBelkyRmxiRGRsWjJVMVptZGxPMjVuZnpSJTJGIiwiQXV0aG5JZCI6IiIsIklzRG91YmxlRW5jcnlwdGVkIjpmYWxzZSwiRGV2aWNlSWQiOiIiLCJSZWZyZXNoRXhwaXJlZEF0IjoxNzEyMTQzODE3fQ.4CFgtlxzVDC2v8T_abiYC833C0UnKv-V1pJL70PUmpM; gcc=ES; gsc=; smd=dba9811c3280618593852affdf73e7af-1709623817; __cf_bm=BlRWzhRM9yBCypYx39aWtQwSeg6BVKgdsY6rDkU6SnI-1709623819-1.0.1.1-NWA6Bw5AeJA8Y_TdMTGLY0n4tUG_6rSDhn.glETiTJD4EtomIpzyqmugYRlTKmgaFBDLeeZyi5ihyk6KxqCnsgCKLMUfCAwCMdAYoo0OVk0; __cflb=02DiuF9qvuxBvFEb2qB2PFFX7ydPK4op77c9RyBkLGQXN; user-browser-sessions=34; browser-session-counted=true; upa=eyJpbnZfcHJvX2Z1bm5lbCI6IjEiLCJtYWluX2FjIjoiMTAiLCJtYWluX3NlZ21lbnQiOiIyIiwiZGlzcGxheV9yZm0iOiIxMjEiLCJhZmZpbml0eV9zY29yZV9hY19lcXVpdGllcyI6IjQiLCJhZmZpbml0eV9zY29yZV9hY19jcnlwdG9jdXJyZW5jaWVzIjoiOCIsImFmZmluaXR5X3Njb3JlX2FjX2N1cnJlbmNpZXMiOiI1IiwiYWN0aXZlX29uX2lvc19hcHAiOiIwIiwiYWN0aXZlX29uX2FuZHJvaWRfYXBwIjoiMCIsImFjdGl2ZV9vbl93ZWIiOiIxIiwiaW52X3Byb191c2VyX3Njb3JlIjoiMTAwIn0%3D; page_view_count=1; lifetime_page_view_count=139; invpc=1; cf_clearance=RGNj4vw1G9I24COeGwGt7gOnhzsF.3qPKcYouk2hpns-1709623821-1.0.1.1-b.2Y6T3lVjqzBdRoUE719axrsd48PU58ETeNQZawHMaQU5.QsBK2IwN63_xhiwwmSbFEXqToqUXyR.VBObtwPA; _hjSession_174945=eyJpZCI6IjExM2NkODFmLWMwZDktNDdlNS04NjM3LWE1OWExZTMzOWE3YiIsImMiOjE3MDk2MjM4MjE0MDYsInMiOjAsInIiOjAsInNiIjowLCJzciI6MCwic2UiOjAsImZzIjowLCJzcCI6MH0=; _hjHasCachedUserAttributes=true; _imntz_error=0; dicbo_id=%7B%22dicbo_fetch%22%3A1709623822416%7D; _ga_C4NDLGKVMK=GS1.1.1709623822.39.0.1709623822.60.0.0; _ga=GA1.2.894918970.1684825276; _gid=GA1.2.409541651.1709623823; AMZN-Token=v2FweLwxQzlPNE9DOWx0NWNvT0h3UHgvSmg0bm5XbHpOdFhXNmJRV0pUVkQ5dzUxQXkrNytqZ1U5VUdkSDl4VlJTYWRwS1BLVlBGb0o4bFNwL00yRzNiODhHbGZXbTg1c3ZoTTNqSStsek12azNzMDhXb29sR3ZycHhiRisxSkJHVXhHZnBVKzZGQ0h3T3VBTitCZEljb0ptQ1hZdjEzQlJ5eHVhakdxMG03dkoyWkQrQUUyVGtBMVMzV2p4QTRrPWJrdgFiaXZ4JERPKy92UVJ2V1RIdnY3M3Z2NzN2djczdnY3M3Z2NzN2djcwPf8=; panoramaId=904310328c866eb0d486a240cf3ea9fb927aee766bdee10e577c1f21aa9c7ad8; panoramaIdType=panoDevice; panoramaId_expiry=1709710223276; _lr_geo_location_state=; _lr_geo_location=ES; connectId={\"ttl\":86400000,\"lastUsed\":1709623823842,\"lastSynced\":1709623823842}; __gads=ID=44203946862b560b:T=1684825381:RT=1709623824:S=ALNI_Mb_rGAasYBGEqbwMmUga1uRnJmypw; __gpi=UID=00000c33767ad8d0:T=1684825381:RT=1709623824:S=ALNI_MY4RLG2VNQOzwgYWbMJ2p6zZU8bfQ; __eoi=ID=03cd43161cf3cde6:T=1707903134:RT=1709623824:S=AA-AfjYxum6B7rT2C9yuKKeLw9_m; cto_bundle=7EJNTV9BUWVjSk82ZTJWZCUyRjYlMkZlNmxwdGVEVyUyQjlZZnNIWXpOQXdkckM3bFhzMEdUU0ttMEdlSHElMkJienlnd2g1M243ayUyQlNScXoxRXE0ZnlzcWR3dFdLQUQ2Q29SeEJsdkJwUkFZVEhOb0JUS2VMaWpqWkYzUk9GRnJUJTJGVzZHVlNrN2JxUjklMkJTUVlBYzhmdHlWQXpSSXglMkZrbXNuJTJGYVlRZUtZZGIzQ0Y3V1psVUxQTVklM0Q");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Elements listaElements = page.getElementsByClass("text-5xl/9 font-bold text-[#232526] md:text-[42px] md:leading-[60px]");
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

	public static ProductoVarDTO getProductoFromCoinmarketcap(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie",
				"bnc-uuid=c233f046-ad59-4f34-a844-6c6293573360; _pbjs_userid_consent_data=3524755945110770; _sharedID=bb2df00f-1369-4a1a-8ce5-b89305f02122; cmc-language=es; x-csrf-token=00076939b5bd80881845eb2ed30c1fed92f794b58ba548e37b1b338e342b6399f697bbd06ab364cf5d7ea637a931974e9920839830b6b7c89e60b8797118929efbe9b25b9af5187a3b15276d9354994507dad03dde4ae45b21a1da238b581832f0e140624cb305ea662cac451feadb89; _sharedID_cst=zix7LPQsHA%3D%3D; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218da72b0db7298-0726e8c0b84aba8-26001851-2073600-18da72b0db8ab2%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkYTcyYjBkYjcyOTgtMDcyNmU4YzBiODRhYmE4LTI2MDAxODUxLTIwNzM2MDAtMThkYTcyYjBkYjhhYjIifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218da72b0db7298-0726e8c0b84aba8-26001851-2073600-18da72b0db8ab2%22%7D; OptanonAlertBoxClosed=2024-03-05T07:55:52.236Z; eupubconsent-v2=CP6_zhgP6_zhgAcABBENAqEsAP_gAEPgAChQg1NX_H__bW9r8Xr3aft0eY1P99j77sQxBhfJE-4FzLvW_JwXx2ExNA36tqIKmRIEu3bBIQFlHJDUTVigaogVryDMakWcgTNKJ6BkiFMRM2dYCF5vmQtj-QKY5vp9d3dx2D-t_dv83dzyz8VHn3e5_2e0eJCdA58tDfv9bROb-9IPd_58v4v0_F_rk2_eT1l_tevp7B8uft87_XU-9_fff78AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEQagCzDQqIA-yJCQi0HCKBACoKwgIoEAAAAJA0QEAJgwKdgYBLrCRACAFAAMEAIAAUZAAgAAEgAQiACQAoEAAEAgUAAIAAAgEADAwABgAtBAIAAQHQIUwIAFAsAEjMiIUwIQoEggJbKBBICgQVwgCLPAggERMFAAACQAVgACAsFgMSSAlYkECXEG0AABAAgEEIFQik6MAQwJmy1U4om0ZWkBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAAACAA.f_wACHwAAAAA; OptanonConsent=isGpcEnabled=0&datestamp=Tue+Mar+05+2024+08%3A55%3A52+GMT%2B0100+(Central+European+Standard+Time)&version=202310.2.0&browserGpcFlag=0&isIABGlobal=false&hosts=&consentId=f6e08570-0782-4f31-aad7-9661760cf519&interactionCount=2&landingPath=NotLandingPage&groups=C0001%3A1%2CC0003%3A1%2CC0004%3A1%2CC0002%3A1%2CV2STACK42%3A1&AwaitingReconsent=false");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Elements listaElements = page.getElementsByClass("faq-answer-wrapper");
		Element element = listaElements.get(0);
		String valorTitulo = element.text().substring(63, 72);
		ProductoVarDTO productoVar = new ProductoVarDTO();
		productoVar.setProductoId(productoId);
		productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(valorTitulo.replaceAll(",", ""))));
		return productoVar;
	}

	public static ProductoVarDTO getProductoFromGoogle(String productoId, String urlScraping) throws Throwable
	{
		Connection connection = Jsoup.connect(urlScraping);
		connection.header("Cookie",
				"HSID=Ad_NCgjfLmD8AN0Ow; SSID=APhJnnHRKLlgNKGLR; APISID=q5pQyq3xdhUck-2R/A3jie3GsvSFxQRTQ1; SAPISID=eM8eQmRg-dykFwb4/AafTBOVSsdhpiczIB; __Secure-1PAPISID=eM8eQmRg-dykFwb4/AafTBOVSsdhpiczIB; __Secure-3PAPISID=eM8eQmRg-dykFwb4/AafTBOVSsdhpiczIB; SEARCH_SAMESITE=CgQIwpkB; ADS_VISITOR_ID=00000000-0000-0000-0000-000000000000; 1P_JAR=2024-2-6-15; SID=g.a000gggYmS-vGbDUrvbJEMofTpZKZQRpe856dsFu1LztexuQpKAO2wyIUx4sin3cKQ4QknGt4QACgYKAZwSAQASFQHGX2MimogTGM_qHwYtW0j67saI3BoVAUF8yKrYpEYWN9jiO9aDTHgm4kqU0076; __Secure-1PSID=g.a000gggYmS-vGbDUrvbJEMofTpZKZQRpe856dsFu1LztexuQpKAOIGdCaPoEcqDuovVvcCrdLQACgYKAZYSAQASFQHGX2MiS9cAqvzg6Ccr9lzR_T4oyxoVAUF8yKrqR_hBqA6YMoeK_wurtoj-0076; __Secure-3PSID=g.a000gggYmS-vGbDUrvbJEMofTpZKZQRpe856dsFu1LztexuQpKAOY4L7gcV4ijDC87c8WQgLbgACgYKAesSAQASFQHGX2Mi7Xd_tD0aXLgO0sOXl9kOYhoVAUF8yKr1akw7EZzXthpnpJHRyiRU0076; NID=512=GogjsFhtesEwoyaUcTTlLZNHxzhBRvzpeIBfXkV2793fispDIViKEamaqitWyvxYHi8_KI2RreIbmJ77Q7MdQxlZCmJiDK9w2D5STmktW3nvNv15RaaUw2OsKk08Dp2Snk1n8bFz6rwDed35tZibYVk_GX-DNz4BR_DiwsV6WOSQrrUb4oFfMHfRAMW3bgfnaIj4CtquQAh3vajB4DDg7QWm2ea2egi7w5yAo1qxhIdGKop2SInEraAwtquwIncwmfEmE-KF21Y1kejmZQm7b5wtei-jgG-5EePl9hwx0SxPtFl3IXGZA5BQGoU1JPKitDwWlSd4ghJCuSNyAqnewEU19Jau36Hizg8VXF9u6r9J0RvPODv2hKvMcA8Jv-iUgbpaPUCbxDfcSjU6mGI-CqWOS4SqvB9bFLRqg5tFe36bfNxGV1SV51NMmCIfxODvkvDF8r1OYCAocn3wMA6OS7MUqKciKZqooNgTsBrYsnt8RnA4peNGkW79dMkTmhZZ9SWQi8_X43CComFme9s; AEC=Ae3NU9PKrcUYHxWbkUqTjh1Sw5SfvLKbdmC8f7V52v54dN0T62cppWE9mw; __Secure-ENID=17.SE=XZ-6dh3VKp-G8FrwFhWnBF0WAR0jJX6wnNOyjW4aoebud-B06sAb1vcBKZgXpFbXQ3NQhd7n9tGhPqFwmrrUUKnA3KWJ8g8-0qz_Q29xrDHu6NG-P3sVcR2Lr9AiSog-dtKXGOjmrzBO9Y6KJBeV6tLt2LXF4z0EXsEtvD3YS66MDLBRfNacn2jcA3OpFR4ZR5MNrVxfgyKXWd6spr3jemU0qQESoqz0G_1EveWrWZwxl3mg-T3ViBQ0S0vOHTvKFQ9yj_1JUESFG2oL5xtSxoQxGyF3OI4nNenbuY2hWfOrXyqs0ZNFyOSVSUS2NjpqUMm-GDtt9jT6H5neEUbeS00CMCFEhRRuPzZOWztH6VAvjsQJgtbkunWRNSYktkZPjqXyQacnoWMeitr4HNfBPyEV--n-VgvJ4aevE5l64HzBaH8qrO5pPjCgMb6K9JOVzJ19Q6wO4JOMgUwEYE-zg7G5VuE; __Secure-1PSIDTS=sidts-CjIBYfD7Z3FA_eICvuEAb7FKQjoWACO8JO10E9XL1y4ZI9eOsJ7WTXfclqw6GpTPRMdQNBAA; __Secure-3PSIDTS=sidts-CjIBYfD7Z3FA_eICvuEAb7FKQjoWACO8JO10E9XL1y4ZI9eOsJ7WTXfclqw6GpTPRMdQNBAA; DV=42EGYtFW2PalMKf_7kRMOXnjdw7b4FiJrxBJHbd8oAIAADB5k-qZxbp87gAAAEiTLS4ARmiUVAAAAAwjI3J9dr-QHAAAgHDg_kzJEK3D2hAAcOIVpget2HcaNwQAgIhfG-bCl2fTDQEAgRFBlMlRLR11QwDAtfCffW4KYMHdEAAA; SIDCC=AKEyXzUbNZpjB3b-xKN5etiXgWph1kRkX9eZuqQyIJ5XjRQC_7VSdzNv4gDaFw7LCMg9Pd96Kzcf; __Secure-1PSIDCC=AKEyXzWhgLwpr4PKTbkzlsc_CuzPYRPGFSsihywg8sC1kDZgbZxBRgi7zrXE3-bM4Lfu03ZOO-C0; __Secure-3PSIDCC=AKEyXzXk9emYz5PCZzyzabhrxTeme9tEl-xmttssoHWwMr6pSlNmyJfr11r0olnTmYZ36yd0TEc");
		connection.header("User-Agent", USER_AGENT);
		Connection.Response response = connection.execute();
		Document page = response.parse();
		Elements listaElements = page.getElementsByClass("pclqee");
		Element element = listaElements.get(0);
		String valorTitulo = element.text();
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
//			productoVar = getProductoFromYahoo("TEST", "https://es.finance.yahoo.com/quote/BTC-EUR");
//			System.out.println(productoVar.getValorTitulo());
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

			productoVar = getProductoFromCoinmarketcap("TEST", "https://coinmarketcap.com/es/currencies/bitcoin/btc/eur");
			System.out.println(productoVar.getValorTitulo());

			productoVar = getProductoFromGoogle("TEST", "https://www.google.com/search?q=btc+eur");
			System.out.println(productoVar.getValorTitulo());

		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
