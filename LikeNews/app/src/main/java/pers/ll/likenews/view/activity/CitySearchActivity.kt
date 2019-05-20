package pers.ll.likenews.view.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.android.synthetic.main.activity_city_select.*
import kotlinx.android.synthetic.main.include_search_toolbar.*
import pers.ll.likenews.R
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.City
import pers.ll.likenews.model.City_Table
import pers.ll.likenews.utils.MainHandler
import pers.ll.likenews.utils.ThreadPoolManager

class CitySearchActivity : AppCompatActivity(), CityListAdapter.OnItemClickListener {

    private lateinit var mAdapter : CityListAdapter
    private var mExecutor = ThreadPoolManager.getInstance()
    private var mMainThread = MainHandler.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_search)
        initView()
        initData()
    }

    private fun initView() {
        initToolbar()
        mAdapter = CityListAdapter(ArrayList(), this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
    }

    private fun initToolbar() {
        barLeft.visibility = View.VISIBLE
        barTitle.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mExecutor.execute {
//                    val sql = "select * from City t where t.countyname like ?"
                    val operator = City_Table.countyname.like(s.toString())
                    val list = SQLite.select().from(City :: class.java).where(operator).queryList() as ArrayList<City>
                    if (list.size > 0) {
                        showData(list)
                    }
                }
            }

        })
        barLeft.setOnClickListener {
            finish()
        }
    }

    private fun showData(list: ArrayList<City>) {
        mMainThread.post {
            mAdapter.replaceAll(list)
            mAdapter.notifyDataSetChanged()

        }
    }

    private fun initData() {
//        mExecutor.execute {
//            //查询
//            val cityList = Select().from(City :: class.java).queryList()
//            if ( cityList.size > 0) {
//                ToastUtils.showShort("城市个数" + cityList.size.toString())
//            } else {
//                //插入
//                val data = MockData(applicationContext, "cities.json")
//                val json = data.json
//                val type = object : TypeToken<ArrayList<City>>() {}.type
//                val cities : ArrayList<City> = Gson().fromJson(json, type)
//                for (city in cities) {
//                    city.save()
//                    ToastUtils.showShort("城市数据自动导入成功")
//                }
//            }
//        }
    }

    override fun onItemClick(city: City) {
        val intent = Intent()
        intent.putExtra(Const.Key.KEY_CITY, city)
        setResult(Activity.RESULT_OK)
        finish()
    }
}
