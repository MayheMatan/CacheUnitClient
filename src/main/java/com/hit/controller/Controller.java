package com.hit.controller;

import java.util.Observer;

public interface Controller extends Observer{

	public void update(java.util.Observable obs, java.lang.Object obj);
}
