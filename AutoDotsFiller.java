package com.project001.lysr;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

public class AutoDotsFiller {
		
		private static final String poweredBy = "LaysarSiXTeeN";
		private static final String codeVersion = "1.0.0";
		private static final String updateNotes = "الإصدار الأول";
		private static final String lastUpdateGregorian = "4/8/2025";
		private static final String lastUpdateHijri = "10/2/1447";
		
		private static final String LOG_TAG = "AutoDotsFiller";
		
		public static void Apply(final String symbolInput, final View... views) {
				if (symbolInput == null || symbolInput.isEmpty() || views == null || views.length == 0) return;
				
				final String symbol = String.valueOf(symbolInput.charAt(0));
				
				for (final View view : views) {
						if (!(view instanceof TextView)) continue;
						
						final TextView textView = (TextView) view;
						
						textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
								private String lastContent = "";
								
								@Override
								public void onLayoutChange(View v, int l, int t, int r, int b, int ol, int ot, int or, int ob) {
										int availableWidth = textView.getWidth() - textView.getPaddingLeft() - textView.getPaddingRight();
										Paint paint = textView.getPaint();
										float symbolWidth = paint.measureText(symbol);
										if (symbolWidth <= 0 || availableWidth <= 0) return;
										int count = (int) (availableWidth / symbolWidth);
										StringBuilder builder = new StringBuilder();
										for (int i = 0; i < count; i++) builder.append(symbol);
										String result = builder.toString();
										if (!result.equals(lastContent)) {
												lastContent = result;
												textView.setText(result);
										}
								}
						});
				}
		}
} 
