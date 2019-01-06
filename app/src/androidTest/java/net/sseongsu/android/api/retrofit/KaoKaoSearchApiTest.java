package net.sseongsu.android.api.retrofit;

import net.sseongsu.android.api.KaKaoSearch;
import net.sseongsu.android.ui.kakaosearch.model.ImageDocument;
import net.sseongsu.android.ui.kakaosearch.model.ImageSearchResultMetaData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;


public class KaoKaoSearchApiTest {

    static final String RESPONSE = "{\n" +
            "    \"documents\": [\n" +
            "        {\n" +
            "            \"collection\": \"cafe\",\n" +
            "            \"datetime\": \"2018-05-05T09:09:01.000+09:00\",\n" +
            "            \"display_sitename\": \"Daum카페\",\n" +
            "            \"doc_url\": \"http://cafe.daum.net/gjsansang/K0dJ/1546?q=%EC%84%A4%ED%98%84&re=1\",\n" +
            "            \"height\": 373,\n" +
            "            \"image_url\": \"http://t1.daumcdn.net/news/201604/20/starnews/20160420104531451vrlt.jpg\",\n" +
            "            \"thumbnail_url\": \"https://search4.kakaocdn.net/argon/130x130_85_c/EQa9bd9jV9t\",\n" +
            "            \"width\": 560\n" +
            "        }\n" +
            "    ],\n" +
            "    \"meta\": {\n" +
            "        \"is_end\": false,\n" +
            "        \"pageable_count\": 3828,\n" +
            "        \"total_count\": 708247\n" +
            "    }\n" +
            "}";

    private final NetworkBehavior behavior = NetworkBehavior.create();
    private KaKaoSearchApi kaKaoSearchApi;

    @Before
    public void setUp() {
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://dapi.kakao.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior).build();
        BehaviorDelegate<KaKaoSearchApi> delegate = mockRetrofit.create(KaKaoSearchApi.class);
        kaKaoSearchApi = new KaKaoSearchApiMock(delegate);
    }


    @Test
    public void searchImage_1() {
        Response<ImageSearchResponse> responseResponse = null;
        try {
            responseResponse = kaKaoSearchApi.searchImage("설현").execute();
        } catch (Throwable ignored) {
        }

        assertNotNull(responseResponse);

        ImageSearchResponse imageSearchResponse = responseResponse.body();
        assertNotNull(imageSearchResponse);
        assertNotNull(imageSearchResponse.getDocuments());
        assertNotNull(imageSearchResponse.getMetaData());

        List<ImageDocument> imageDocumentList = imageSearchResponse.getDocuments();
        Assert.assertEquals(1, imageDocumentList.size());
        ImageDocument document = imageDocumentList.get(0);
        assertEquals("cafe", document.getCollection());
        assertEquals("2018-05-05T09:09:01.000+09:00", document.getDatetime());
        assertEquals("Daum카페", document.getDisplaySiteName());
        assertEquals("http://cafe.daum.net/gjsansang/K0dJ/1546?q=%EC%84%A4%ED%98%84&re=1", document.getDocUrl());
        assertNotNull(document.getHeight());
        assertEquals(373, document.getHeight().intValue());
        assertEquals("http://t1.daumcdn.net/news/201604/20/starnews/20160420104531451vrlt.jpg", document.getImageUrl());
        assertEquals("https://search4.kakaocdn.net/argon/130x130_85_c/EQa9bd9jV9t", document.getThumbnailUrl());
        assertNotNull(document.getWidth());
        assertEquals(560, document.getWidth().intValue());

        ImageSearchResultMetaData metaData = imageSearchResponse.getMetaData();
        assertNotNull(metaData.isEnd());
        assertFalse(metaData.isEnd());
        assertNotNull(metaData.getPageableCount());
        assertEquals(3828, metaData.getPageableCount().intValue());
        assertNotNull(metaData.getTotalCount());
        assertEquals(708247, metaData.getTotalCount().intValue());
    }

    @Test
    public void searchImage_2() {
        Response<ImageSearchResponse> responseResponse = null;
        try {
            responseResponse = kaKaoSearchApi.searchImage("설현", KaKaoSearch.SortType.accuracy.name(), 1, 1).execute();
        } catch (Throwable ignored) {
        }

        assertNotNull(responseResponse);

        ImageSearchResponse imageSearchResponse = responseResponse.body();
        assertNotNull(imageSearchResponse);
        assertNotNull(imageSearchResponse.getDocuments());
        assertNotNull(imageSearchResponse.getMetaData());

        List<ImageDocument> imageDocumentList = imageSearchResponse.getDocuments();
        Assert.assertEquals(1, imageDocumentList.size());
        ImageDocument document = imageDocumentList.get(0);
        assertEquals("cafe", document.getCollection());
        assertEquals("2018-05-05T09:09:01.000+09:00", document.getDatetime());
        assertEquals("Daum카페", document.getDisplaySiteName());
        assertEquals("http://cafe.daum.net/gjsansang/K0dJ/1546?q=%EC%84%A4%ED%98%84&re=1", document.getDocUrl());
        assertNotNull(document.getHeight());
        assertEquals(373, document.getHeight().intValue());
        assertEquals("http://t1.daumcdn.net/news/201604/20/starnews/20160420104531451vrlt.jpg", document.getImageUrl());
        assertEquals("https://search4.kakaocdn.net/argon/130x130_85_c/EQa9bd9jV9t", document.getThumbnailUrl());
        assertNotNull(document.getWidth());
        assertEquals(560, document.getWidth().intValue());

        ImageSearchResultMetaData metaData = imageSearchResponse.getMetaData();
        assertNotNull(metaData.isEnd());
        assertFalse(metaData.isEnd());
        assertNotNull(metaData.getPageableCount());
        assertEquals(3828, metaData.getPageableCount().intValue());
        assertNotNull(metaData.getTotalCount());
        assertEquals(708247, metaData.getTotalCount().intValue());
    }
}
