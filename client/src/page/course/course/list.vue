<template>
  <!-- 选项 -->
  <div>
    <el-button type="primary" size="small" @click="handle_add">添加</el-button>
  </div>
  <!-- 表格 -->
  <div>
    <el-table :data="state.list" style="width: 100%">
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="code" label="课程代码" />
      <el-table-column prop="description" label="课程描述" />
      <el-table-column prop="name" label="课程名称" />
      <el-table-column prop="semester" label="学期" />
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
import { Pagination, Course } from '../../../types/dto';
import { CourseAPI } from '../../../api';

const state = reactive({
  list: <Array<Course>>[]
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

async function handle_del(item: Course) {
  const data = await CourseAPI.del({ id: item.courseId })
  init()
}

async function init() {
  const data = await CourseAPI.list(pagination.value)
  state.list = data.list
  console.log(data);

}

onMounted(() => {
  init()
})

</script>

<style scoped></style>