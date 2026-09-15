/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.dsl.jbang.core.common;

import org.apache.camel.util.xml.pretty.XmlPrettyPrinter;
import org.jline.jansi.Ansi;

/**
 * Renders XML for the terminal. Lives in the CLI module because the ANSI colouring depends on jline, which
 * camel-jbang-export must not pull in.
 */
public final class XmlPrinter {

    private XmlPrinter() {
    }

    /**
     * Prints the XML in pretty mode (no color).
     */
    public static String prettyPrint(String xml, int spaces) throws Exception {
        return XmlPrettyPrinter.pettyPrint(xml, spaces);
    }

    /**
     * Prints the XML with ANSi color (similar to jq)
     */
    public static String colorPrint(String xml, int spaces, boolean pretty) throws Exception {
        return XmlPrettyPrinter.colorPrint(xml, spaces, pretty, new XmlPrettyPrinter.ColorPrintElement() {
            @Override
            public String color(int type, String value) {
                String s = value != null ? value : "null";
                if (type == XmlPrettyPrinter.ColorPrintElement.DECLARATION) {
                    s = Ansi.ansi().fgBrightDefault().a(Ansi.Attribute.INTENSITY_FAINT).a(s).reset().toString();
                } else if (type == XmlPrettyPrinter.ColorPrintElement.ELEMENT) {
                    s = Ansi.ansi().fgBright(Ansi.Color.BLUE).a(s).reset().toString();
                } else if (type == XmlPrettyPrinter.ColorPrintElement.VALUE) {
                    s = Ansi.ansi().fgDefault().a(s).reset().toString();
                } else if (type == XmlPrettyPrinter.ColorPrintElement.ATTRIBUTE_KEY) {
                    s = Ansi.ansi().fg(Ansi.Color.MAGENTA).a(s).reset().toString();
                } else if (type == XmlPrettyPrinter.ColorPrintElement.ATTRIBUTE_VALUE
                        || type == XmlPrettyPrinter.ColorPrintElement.ATTRIBUTE_QUOTE) {
                    s = Ansi.ansi().fg(Ansi.Color.GREEN).a(s).reset().toString();
                }
                return s;
            }
        });
    }

}
