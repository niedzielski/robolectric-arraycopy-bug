package com.niedzielski.android.robolectricarraycopybug;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.Html;

import org.codehaus.plexus.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE) public class ArraycopyTest {
    private static final String HTML_SHORT = "<img src='foo.png'>";
    private static final String HTML_LONG = String.format("<img src='%s.png'>", StringUtils.repeat("foo", 100));

    @Config(sdk = Build.VERSION_CODES.JELLY_BEAN)
    @Test public void testArraycopyLegacyShort() {
        //noinspection deprecation
        Html.fromHtml(HTML_SHORT, null, null);
    }

    @Config(sdk = Build.VERSION_CODES.JELLY_BEAN)
    @Test public void testArraycopyLegacyLong() {
        //noinspection deprecation
        Html.fromHtml(HTML_LONG, null, null);
    }

    @TargetApi(Build.VERSION_CODES.N) @Config(sdk = Build.VERSION_CODES.N)
    @Test public void testArraycopyShort() {
        Html.fromHtml(HTML_SHORT, Html.FROM_HTML_MODE_LEGACY, null, null);
    }

    // Only this test fails
    @TargetApi(Build.VERSION_CODES.N) @Config(sdk = Build.VERSION_CODES.N)
    @Test public void testArraycopyLong() {
        Html.fromHtml(HTML_LONG, Html.FROM_HTML_MODE_LEGACY, null, null);
    }
}
