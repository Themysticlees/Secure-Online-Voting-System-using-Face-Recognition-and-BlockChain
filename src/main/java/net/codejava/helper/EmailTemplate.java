package net.codejava.helper;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplate {
    
    public String getTemplate(String f, String s, String t){

        String message = "<!DOCTYPE html>\n"+
"<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n"+
"<head>\n"+
"<title></title>\n"+
"<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n"+
"<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\n"+
"<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n"+
"<style> \n"+
"		* { \n"+
"			box-sizing: border-box;\n"+
"		}\n"+
"		body {\n"+
"			margin: 0;\n"+
"			padding: 0;\n"+
"		}\n"+
"		a[x-apple-data-detectors] {\n"+
"			color: inherit !important;\n"+
"			text-decoration: inherit !important;\n"+
"		}\n"+
"		#MessageViewBody a {\n"+
"			color: inherit;\n"+
"			text-decoration: none;\n"+
"		}\n"+
"		p { \n"+
"			line-height: inherit \n"+
"		} \n"+
"		.desktop_hide,\n"+
"		.desktop_hide table {\n"+
"			mso-hide: all;\n"+
"			display: none;\n"+
"			max-height: 0px;\n"+
"			overflow: hidden;\n"+
"		}\n"+
"		@media (max-width:660px) {\n"+
"			.desktop_hide table.icons-inner {\n"+
"				display: inline-block !important;\n"+
"			}\n"+
"			.icons-inner {\n"+
"				text-align: center;\n"+
"			}\n"+
"			.icons-inner td {\n"+
"				margin: 0 auto;\n"+
"			}\n"+
"			.row-content {\n"+
"				width: 100% !important;\n"+
"			}\n"+
"			.image_block img.big {\n"+
"				width: auto !important;\n"+
"			}\n"+
"			.column .border,\n"+
"			.mobile_hide {\n"+
"				display: none;\n"+
"			}\n"+
"			table {\n"+
"				table-layout: fixed !important;\n"+
"			}\n"+
"			.stack .column {\n"+
"				width: 100%;\n"+
"				display: block;\n"+
"			}\n"+
"			.mobile_hide {\n"+
"				min-height: 0;\n"+
"				max-height: 0;\n"+
"				max-width: 0;\n"+
"				overflow: hidden;\n"+
"				font-size: 0px;\n"+
"			}\n"+
"			.desktop_hide,\n"+
"			.desktop_hide table {\n"+
"				display: table !important;\n"+
"				max-height: none !important;\n"+
"			}\n"+
" } \n"+
" </style> \n"+
"</head>\n"+

"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td style=\"padding-left:40px;padding-right:40px;width:100%;\">\n"+
"<div align=\"center\" style=\"line-height:10px\"><img alt=\"I'm an image\" src=\"https://github.com//Themysticlees//Secure-Online-Voting-System--Beta-//blob//master//src//main//resources//static//images//loader.png?raw=true\" style=\"display: block; height: auto; border: 0; width: 352px; max-width: 100%;\" title=\"I'm an image\" width=\"352\"/></div>\n"+
"</td>\n"+
"</tr>\n"+
"</table>\n"+

"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"+
"<tr>\n"+
"<td style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:10px;\">\n"+
"<div style=\"font-family: sans-serif\">\n"+
"<div class=\"txtTinyMce-wrapper\" style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;\">\n"+



"<p style=\"margin: 0; font-size: 16px; text-align: center;\"><span style=\"font-size:30px;color:#2b303a;\"><strong>"+f+"</strong></span></p>\n"+



"</div>\n"+
"</div>\n"+
"</td>\n"+
"</tr>\n"+
"</table>\n"+
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"+
"<tr>\n"+
"<td style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:10px;\">\n"+
"<div style=\"font-family: sans-serif\">\n"+
"<div class=\"txtTinyMce-wrapper\" style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px; color: #555555; line-height: 1.5;\">\n"+



"<p style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 22.5px;\"><span style=\"color:#808389;font-size:15px;\">"+s+"</span></p>\n"+



"</div>\n"+
"</div>\n"+
"</td>\n"+
"</tr>\n"+
"</table>\n"+
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td style=\"padding-left:10px;padding-right:10px;padding-top:15px;text-align:center;\">\n"+
"<div align=\"center\">\n" +
"<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" style=\"height:62px;width:203px;v-text-anchor:middle;\" arcsize=\"97%\" stroke=\"false\" fillcolor=\"#1aa19c\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#ffffff; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]-->\n"+


"<div style=\"text-decoration:none;display:inline-block;color:#ffffff;background-color:#1aa19c;border-radius:60px;width:auto;border-top:1px solid #1aa19c;font-weight:undefined;border-right:1px solid #1aa19c;border-bottom:1px solid #1aa19c;border-left:1px solid #1aa19c;padding-top:15px;padding-bottom:15px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;text-align:center;mso-border-alt:none;word-break:keep-all;\"><span style=\"padding-left:30px;padding-right:30px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span style=\"font-size: 16px; margin: 0; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>"+t+"</strong></span></span></div>\n"+


"<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n"+
"</div>\n"+
"</td>\n"+
"</tr>\n"+
"</table>\n"+
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td style=\"padding-bottom:12px;padding-top:60px;\">\n"+
"<div align=\"center\">\n"+
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n"+
"</tr>\n"+
"</table>\n"+
"</div>\n"+
"</td>\n"+
"</tr>\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</tbody>\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</tbody>\n"+
"</table>\n"+
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tbody>\n"+
"<tr>\n"+
"<td>\n"+
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f8f8f9; color: #000000; width: 640px;\" width=\"640\">\n"+
"<tbody>\n"+
"<tr>\n"+
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"+
"<table border=\"0\" cellpadding=\"20\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td>\n"+
"<div align=\"center\">\n"+
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n"+
"</tr>\n"+
"</table>\n"+
"</div>\n"+
"</td>\n"+
"</tr>\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</tbody>\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</tbody>\n"+
"</table>\n"+
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tbody>\n"+
"<tr>\n"+
"<td>\n"+
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #2b303a; color: #000000; width: 640px;\" width=\"640\">\n"+
"<tbody>\n"+
"<tr>\n"+
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 0px; padding-bottom: 0px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"+
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td>\n"+
"<div align=\"center\">\n"+
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 4px solid #1AA19C;\"><span> </span></td>\n"+
"</tr>\n"+
"</table>\n"+
"</div>\n"+
"</td>\n"+
"</tr>\n"+
"</table>\n"+
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"social_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td style=\"padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:8px;text-align:center;\">\n"+
"</td>\n"+
"</tr>\n"+
"</tbody>\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</tbody>\n"+
"</table>\n"+
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-7\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tbody>\n"+
"<tr>\n"+
"<td>\n"+
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px;\" width=\"640\">\n"+
"<tbody>\n"+
"<tr>\n"+
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"+
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n"+
"<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"+
"<tr>\n"+
"<td style=\"vertical-align: middle; text-align: center;\">\n"+
"<!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n"+
"<!--[if !vml]><!-->\n"+
"<table cellpadding=\"0\" cellspacing=\"0\" class=\"icons-inner\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\">\n"+
"<!--<![endif]-->\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</tbody>\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</tbody>\n"+
"</table>\n"+
"</td>\n"+
"</tr>\n"+
"</t\nbody>\n"+
"</table>\n"+
"</body>\n"+
"</html>\n";

    return message;

    }
}
