package com.example.b2bcamp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInfoVo {

@SerializedName("result")
@Expose
private List<ProductResultVo> result = null;

public List<ProductResultVo> getResult() {
return result;
}

public void setResult(List<ProductResultVo> result) {
this.result = result;
}

}