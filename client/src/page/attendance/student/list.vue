<template>
  <!-- 选项 -->
  <div>
    <el-button type="primary" size="small" @click="handle_add">添加</el-button>
  </div>
  <!-- 表格 -->
  <div>
    <el-table :data="state.list" style="width: 100%">
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="date" label="日期" />
      <el-table-column prop="present" label="是否在场" >
        <template #default="scope">
          <div>{{ scope.row.present?"在场":"不在场" }}</div>  
        </template>
      </el-table-column>
      <el-table-column prop="section.course.name" label="课程" />
      <el-table-column prop="section.location" label="授课教室" />
      <el-table-column prop="teacherId" label="记录老师ID" />
      <el-table-column prop="student.id" label="学生ID" />
      <el-table-column prop="student.name" label="学生名称" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" size="small" @click="handle_edit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认要删除吗?" @confirm="handle_del(scope.row)">
            <template #reference>
              <el-button type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
  </div>
  <Edit :dialogFormVisible="dialogFormVisible" :val="dialogVal" @close="handle_close" @success="handle_success"></Edit>
</template>

<script setup lang="ts">

import { onMounted, reactive, ref } from 'vue';

import Edit from './edit.vue'
import _ from 'lodash'
import { ElNotification } from 'element-plus';
import { Attendance, Pagination, Teacher } from '../../../types/dto';
import { AttendanceAPI } from '../../../api';

const state = reactive({
  list: <Array<Attendance>>[]
})
const pagination = ref<Pagination>({
  size: 10,
  keyword: "",
  page: 1
})
// getCarPortListByCommunityId
const dialogFormVisible = ref(false)
const dialogVal = ref<any>()
function handle_close() {
  dialogFormVisible.value = false
}
function handle_success(args: any) {
  // Notification
  ElNotification({
    title: "通知",
    message: args
  })
  init()
}
function handle_edit(item: any) {
  dialogFormVisible.value = true
  const newItem = _.cloneDeep(item)
  dialogVal.value = newItem
}

function handle_add() {
  dialogFormVisible.value = true
  dialogVal.value = {}
}

async function handle_del(item: Attendance) {
  const data = await AttendanceAPI.del({ id: item.attendanceId })
  init()
}

async function init() {
  const data = await AttendanceAPI.studentList(pagination.value)
  state.list = data.list
  console.log(data);

}

onMounted(() => {
  init()
})

</script>

<style scoped></style>