/*******************************************************************************
 GitHubLoader.java is part of the TD-Bot project

 TD-Bot is the Discord-Bot of the TD-Nation Discord Server.
 Copyright (C) 2020 Henrik Steffens

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <https://www.gnu.org/licenses/>.

 Last edit: 2020/11/6
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.loader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class GitHubLoader
{
    public static final String BASE_URL = "https://api.github.com/repos/th3ph4nt0m/TD-Bot";


    /**
     * gets the tag name of the latest github release
     *
     * @return name of the release
     */
    public String getLatestTagName()
    {
        try {
            URL url = new URL(BASE_URL + "/releases");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();


            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }

                scanner.close();

                JSONArray array = new JSONArray(inline.toString());
                JSONObject obj = array.getJSONObject(0);
                return obj.getString("tag_name");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
