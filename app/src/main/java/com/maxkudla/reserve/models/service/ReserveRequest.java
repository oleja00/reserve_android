package com.maxkudla.reserve.models.service;

public class ReserveRequest {
//    {"message":"reserve_request","data":{"_id":"5921ea213782e719212939b1","createdAt":"2017-05-21T19:27:29.130Z","updatedAt":"2017-05-21T19:27:50.468Z","client":{"_id":"58fcfe7aaa2ddb2605ead7d9","createdAt":"2017-04-23T19:20:26.709Z","updatedAt":"2017-04-23T19:20:26.709Z","phone":"380931211095","__v":0,"type":1},"service":{"_id":"590cd46e385a308bd37e82f3","createdAt":"2017-05-05T19:37:18.483Z","updatedAt":"2017-05-05T19:43:24.663Z","latitude":48.51655960083,"longitude":35.048164367676,"gis_id":"70000001022678111_ge7d2ep8p64ACAAC9AI2GGG0Addg9g26G6G419B8G7I24A88rgewB4A8AC7IGG6G0J3C5B8C9938uvG44C17A9ABJ2667867HH39","__v":0,"user":{"_id":"590cd4bdf969902dae0b3a98","createdAt":"2017-05-05T19:38:37.417Z","updatedAt":"2017-05-05T19:38:37.417Z","phone":"380964854445","__v":0,"type":0},"category":"58f8c570e2d087083269971c","name":"Магазин-кафе","address_name":"Калиновая, 89а/1","options":{"cuisines":["58f9e0aa7866391ab6f554bc"],"smoking":["smoking","no_smoking"],"worktime":{"end":24,"start":10}},"about":"The restaurant is located in a historic Excelsior brick building dating from 1886. Creating a sense of the natural evolution of the building was a goal of the designer, Kara Karpenske of Kamarron Design, Inc. (KDI) of Edina. New rustic floors, tall booths, oversized chandeliers, and industrial lighting enhance the look of the exposed original brick. Antiqued mirrored panels set off the white marble design bar top and warm toned leather bar stools create an inviting space to enjoy Chef Eli’s crafted cocktail menu regionally sourced from such boutique distilleries as 45th Parallel Vodka (New Richmond, WI), North Shore Gin #11 (Lake Bluff, IL), Cedar Ridge Iowa Bourbon (Swisher, Iowa), and locally produced Bittercube Bitters (Minneapolis, MN). A large selection of wine and MN sourced tap beers complement the full bar.","subtitle":"Service subtitle","photos":[],"price":2,"rating":0,"available":true},"query":{"_id":"5921ea213782e719212939af","createdAt":"2017-05-21T19:27:29.054Z","updatedAt":"2017-05-21T19:27:29.054Z","client":"58fcfe7aaa2ddb2605ead7d9","options":{"worktill":24,"smoking":"no_smoking","hookah":false,"cuisines":["58f9e0aa7866391ab6f554bc"]},"location":{"latitude":48.51660399999999,"longitude":35.04638699999998},"distance":0.5,"guests":2,"category":"58f8c570e2d087083269971c","__v":0},"distance":0.08134708484048186,"__v":0,"status":"reserved_from_client"}}
//    {"message":"reserve_request","data":{"_id":"5921ea213782e719212939b1","createdAt":"2017-05-21T19:27:29.130Z","updatedAt":"2017-05-21T19:27:50.468Z","client":{"_id":"58fcfe7aaa2ddb2605ead7d9","createdAt":"2017-04-23T19:20:26.709Z","updatedAt":"2017-04-23T19:20:26.709Z","phone":"380931211095","__v":0,"type":1},"service":{"_id":"590cd46e385a308bd37e82f3","createdAt":"2017-05-05T19:37:18.483Z","updatedAt":"2017-05-05T19:43:24.663Z","latitude":48.51655960083,"longitude":35.048164367676,"gis_id":"70000001022678111_ge7d2ep8p64ACAAC9AI2GGG0Addg9g26G6G419B8G7I24A88rgewB4A8AC7IGG6G0J3C5B8C9938uvG44C17A9ABJ2667867HH39","__v":0,"user":{"_id":"590cd4bdf969902dae0b3a98","createdAt":"2017-05-05T19:38:37.417Z","updatedAt":"2017-05-05T19:38:37.417Z","phone":"380964854445","__v":0,"type":0},"category":"58f8c570e2d087083269971c","name":"Магазин-кафе","address_name":"Калиновая, 89а/1","options":{"cuisines":["58f9e0aa7866391ab6f554bc"],"smoking":["smoking","no_smoking"],"worktime":{"end":24,"start":10}},"about":"The restaurant is located in a historic Excelsior brick building dating from 1886. Creating a sense of the natural evolution of the building was a goal of the designer, Kara Karpenske of Kamarron Design, Inc. (KDI) of Edina. New rustic floors, tall booths, oversized chandeliers, and industrial lighting enhance the look of the exposed original brick. Antiqued mirrored panels set off the white marble design bar top and warm toned leather bar stools create an inviting space to enjoy Chef Eli’s crafted cocktail menu regionally sourced from such boutique distilleries as 45th Parallel Vodka (New Richmond, WI), North Shore Gin #11 (Lake Bluff, IL), Cedar Ridge Iowa Bourbon (Swisher, Iowa), and locally produced Bittercube Bitters (Minneapolis, MN). A large selection of wine and MN sourced tap beers complement the full bar.","subtitle":"Service subtitle","photos":[],"price":2,"rating":0,"available":true},"query":{"_id":"5921ea213782e719212939af","createdAt":"2017-05-21T19:27:29.054Z","updatedAt":"2017-05-21T19:27:29.054Z","client":"58fcfe7aaa2ddb2605ead7d9","options":{"worktill":24,"smoking":"no_smoking","hookah":false,"cuisines":["58f9e0aa7866391ab6f554bc"]},"location":{"latitude":48.51660399999999,"longitude":35.04638699999998},"distance":0.5,"guests":2,"category":"58f8c570e2d087083269971c","__v":0},"distance":0.08134708484048186,"__v":0,"status":"reserved_from_client"}}

    private String message;
    private ReserveService data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReserveService getData() {
        return data;
    }

    public void setData(ReserveService data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String s = " message " + message;
        if (data != null) {
            s += " data " + data.toString();
        }


        return s;
    }

}
