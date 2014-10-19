package pygman.invoice.util.jxl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {

	public static WritableWorkbook cWorkbook(OutputStream os) {
		try {
			return Workbook.createWorkbook(os);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static WritableSheet cSheet(WritableWorkbook b, int idx, String name) {
		return b.createSheet(name, idx - 1);
	}

	public static Label cLabel(int a, int b, String value) {
		return new Label(b - 1, a - 1, value);
	}

	public static void aLabelToSheet(Label l, WritableSheet s) {
		try {
			s.addCell(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sColumnSize(WritableSheet s, int idx, int width) {
		s.setColumnView(idx - 1, width);
	}

	public static void sRowSize(WritableSheet s, int idx, int height) {
		try {
			s.setRowView(idx - 1, height * 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sMerge(WritableSheet s, int a, int b, int c, int d) {
		try {
			s.mergeCells(b - 1, a - 1, d - 1, c - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<Integer, Alignment> alignMap = new HashMap<Integer, Alignment>();
	static {
		alignMap.put(0, Alignment.LEFT);
		alignMap.put(1, Alignment.CENTRE);
		alignMap.put(2, Alignment.RIGHT);
	}

	public static void sLabelStyle(Label l, String fontName, int fontSize,
			Colour colour, Colour bgColour, int align, String borderStyle) {

		try {
			if (colour == null)
				colour = Colour.BLACK;
			if (bgColour == null)
				bgColour = Colour.WHITE;
			WritableFont wf = new WritableFont(
					WritableFont.createFont(fontName), fontSize,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					colour);

			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setBackground(bgColour);
			wcf.setAlignment(Alignment.CENTRE);

			if (borderStyle != null && borderStyle.length() == 4) {
				char[] bs = borderStyle.toCharArray();
				if (bs[0] == '1') {
					wcf.setBorder(Border.TOP, BorderLineStyle.THIN,
							jxl.format.Colour.BLACK);
				} else if (bs[0] == '2') {
					wcf.setBorder(Border.TOP, BorderLineStyle.MEDIUM,
							jxl.format.Colour.BLACK);
				}
				if (bs[1] == '1') {
					wcf.setBorder(Border.BOTTOM, BorderLineStyle.THIN,
							jxl.format.Colour.BLACK);
				} else if (bs[1] == '2') {
					wcf.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM,
							jxl.format.Colour.BLACK);
				}
				if (bs[2] == '1') {
					wcf.setBorder(Border.LEFT, BorderLineStyle.THIN,
							jxl.format.Colour.BLACK);
				} else if (bs[2] == '2') {
					wcf.setBorder(Border.LEFT, BorderLineStyle.MEDIUM,
							jxl.format.Colour.BLACK);
				}
				if (bs[3] == '1') {
					wcf.setBorder(Border.RIGHT, BorderLineStyle.THIN,
							jxl.format.Colour.BLACK);
				} else if (bs[3] == '2') {
					wcf.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM,
							jxl.format.Colour.BLACK);
				}
			}

			l.setCellFormat(wcf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
