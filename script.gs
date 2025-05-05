function doPost(e) {
  try {
    var spreadsheet = SpreadsheetApp.getActiveSpreadsheet();
    var data = JSON.parse(e.postData.contents);
    var sheet;

    switch (data.type) {
      case "registercountry":
        // Get the "Current Data" sheet
        sheet = spreadsheet.getSheetByName("Current Data");

        // Check if the sheet exists
        if (!sheet) {
          throw new Error("Sheet 'Current Data' does not exist.");
        }

        const startRow = sheet.getLastRow() + 1; // Start at the next available row

        // Insert values into specific columns
        sheet.getRange(startRow, 8).setValue(data.countryname || ""); // Column H
        sheet.getRange(startRow, 9).setValue(data.gdp || "");         // Column I
        sheet.getRange(startRow, 12).setValue(data.population || "");// Column L
        break;

      case "submission":
        // Get the appropriate sheet based on the category
        switch (data.category) {
          case "Citizen":
            sheet = spreadsheet.getSheetByName("Citizen");
            break;
          case "Investment":
            sheet = spreadsheet.getSheetByName("Investment");
            break;
          case "Company":
            sheet = spreadsheet.getSheetByName("Company");
            break;
          case "CompanyAction":
            sheet = spreadsheet.getSheetByName("CompanyAction");
            break;
          case "DiplomaticAction":
            sheet = spreadsheet.getSheetByName("DiplomaticAction");
            break;
          case "Technology":
            sheet = spreadsheet.getSheetByName("Technology");
            break;
          default:
            // Default sheet if category doesn't match
            sheet = spreadsheet.getSheetByName("Misc");
            break;
        }

        // If the sheet doesn't exist, create it
        if (!sheet) {
          sheet = spreadsheet.insertSheet(data.category);
          // Optionally set up headers for the new sheet
          sheet.appendRow(["Timestamp", "Author", "Content", "Image URLs", "Approved By"]);
        }

        // Append the data to the appropriate sheet
        sheet.appendRow([
          new Date(),          // Timestamp
          data.author || "",   // Author
          data.content || "",  // Content
          data.imageUrls || "",// Image URLs
          data.approvedBy || ""// Approved By
        ]);
        break;

      default:
        // Handle unknown data types
        throw new Error("Unknown data type: " + data.type);
    }

    // Return success response
    return ContentService.createTextOutput("Success")
      .setMimeType(ContentService.MimeType.TEXT);

  } catch (error) {
    // Log and return error response
    Logger.log("Error: " + error.message);
    return ContentService.createTextOutput("Error: " + error.message)
      .setMimeType(ContentService.MimeType.TEXT);
  }
}
