Feature: AS a user I want to Verify the tab menu functionality on nopCommerce.com
  I want to ensure that the platform's tab menu works correctly
  So that navigation on the platform is smooth

 @SmokeTest
  Scenario: Navigate through tab menu and verify submenus
    Given Navigate to NonCommerce WebSite
    When I hover on the "Computers" tab
    Then I should see the following submenus:
      | Desktops |
      | Notebooks |
      | Software |

    When I hover on the "Electronics" menu
    Then I should see the following submenus:
      | Camera & Photo|
      | Cell Phones |
      | Others |

   When I hover on "Apparel" button
   Then I should see the following submenus:
   | Shoes |
   | Clothing |
   | Accessories |

   When I Click on "Digital downloads" menu
   Then I should be on The Digital Downloads page

   When I Click on "Books" page
   Then I should be on the Books page

   When I Click on "Jewellery" word
   Then I should  be on The Jewellery page

