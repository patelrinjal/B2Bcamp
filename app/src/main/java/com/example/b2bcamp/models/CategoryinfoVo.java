package com.example.b2bcamp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryinfoVo {

@SerializedName("result")
@Expose
private List<CategoryResultVo> categoryResultVo = null;

public List<CategoryResultVo> getCategoryResultVo() {
return categoryResultVo;
}

public void setCategoryResultVo(List<CategoryResultVo> categoryResultVo) {
this.categoryResultVo = categoryResultVo;
}

}