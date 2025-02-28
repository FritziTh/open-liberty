/*******************************************************************************
 * Copyright (c) 2016, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

var editorMessages = {
"VALUE": "値",
"SELECT": "選択",
"ADD": "追加",
"OK": "OK",
"CANCEL": "キャンセル",
"ADD_CHILD": "子の追加",
"REMOVE": "削除",
"TEST" : "テスト",
"TEST_CONNECTION": "接続テスト",
"TEST_CONNECTION_DESCRIPTION": "接続をテストする",
"TEST_CONNECTION_DISABLED_DESCRIPTION": "変更を保存して、接続をテストします",
"DESCRIPTION": "説明",
"PREVIOUS": "前へ",
"NEXT": "次へ",
"REMOVE_ELEMENT_CONFIRMATION": "エレメントを削除してもよろしいですか?",
"YES": "Yes",
"NO": "No",
"LOADING": "ロード中...",
"SERVER_CONFIGURATION_EDITOR": "サーバー構成ツール",
"CONFIGURATION_FILES": "構成ファイル",
"TOGGLE_NAVIGATION": "ナビゲーションの切り替え",
"CLOSE": "閉じる",
"ERROR_ACCESSING_SERVER_CONFIGURATION_FILE": "サーバー構成ファイル {0} にアクセスできませんでした。",
"ERROR_ACCESSING_SERVER_SCHEMA_FILE": "スキーマ・ファイル {0} にアクセスできませんでした。",
"NO_MATCHES_FOUND": "一致するものが見つかりません。",
"DEFAULT_VALUE_PLACEHOLDER": "{0} (デフォルト)",
"DEFAULT_VALUE_PLACEHOLDER_WITH_VARIABLE": "{0} (デフォルト) または ${{1}} (定義されている場合)",
"VARIABLE_VALUE_PLACEHOLDER": "{0} (定義されている場合)",
"DEFAULT_SUFFIX": "(デフォルト)",
"SAVE": "保存(S)",
"DISCARD": "廃棄",
"ENHANCED_LABELS": "拡張ラベル",
"FIELD_DESCRIPTIONS": "フィールドの説明",
"MALFORMED_XML": "XML コンテンツは形式が誤っているようです。編集のために {0} に切り替えます。",
"DESIGN": "設計",
"SOURCE": "ソース",
"SOURCE_PANE": "ソース・ペイン",
"LINE_NUMBERS": "行番号",
"DOCUMENTATION_DEFAULT": "デフォルト: {0}",
"REQUIRED_SUFFIX": "(必須)",
"USER_NAME": "ユーザー名",
"USER_NAME_OPTIONAL": "ユーザー名 (オプション)",
"PASSWORD": "パスワード",
"PASSWORD_OPTIONAL": "パスワード (オプション)",
"SIGN_IN": "サインイン",
"ONE_MOMENT_PLEASE": "しばらくお待ちください...",
"LOGIN_FAIL": "ログオンできませんでした。再試行してください。",
"FAILED" : "失敗。",
"FAILED_HTTP_CODE" : "要求が失敗し、HTTP 状況コード {0} が出されました。",
"SUCCESS" : "成功。",
"RESPONSE": "応答",
"PARAMETERS": "パラメーター",
"MISSING_USER_NAME": "ユーザー名を指定してください", 
"SIGN_OUT": "サインアウト",
"SIGN_OUT_ERROR": "サインアウト中にエラーが発生しました",
"SIGN_OUT_PROMPT": "サインアウトしますか?",
"CHANGES_SAVED": "変更は保存されました",
"FILE_ACCESS_ERROR_MESSAGE": "サーバー構成ファイルにアクセスしようとしてエラーが発生しました",
"ERROR_SAVING_FILE_MESSAGE": "変更は保存されませんでした。 サーバーがダウンしているか、ファイルが読み取り専用であるか、または構成変更の権限がないロールに属している可能性があります。",
"ERROR": "エラー",
"UNSAVED_CHANGES_MESSAGE": "保存されていない変更が存在します。",
"READ_ONLY": "読み取り専用",
"RESTRICTED_OR_UNAVAILABLE": "制限されているか、使用不可です",
"EXPLORE_INCLUDES": "インクルードの探索",
"OPEN": "開く",
"READ_ONLY_WARNING_MESSAGE": "このサーバーにはリモート・ファイル・アクセスは構成されていません。 ファイルは読み取り専用モードで使用可能です。 書き込みアクセスを有効にするには、server.xml ファイルに次のエレメントを追加してください。",
"SAVE_BEFORE_CLOSING_DIALOG_TITLE": "閉じる",
"SAVE_BEFORE_CLOSING_DIALOG_MESSAGE": "閉じる前に変更を {0} に保存しますか?",
"DONT_SAVE": "保存しない",
"RETURN_TO_EDITOR": "エディターに戻る",
"THE_VALUE_SHOULD_BE_A_BOOLEAN": "値は true または false でなければなりません。",
"THE_VALUE_SHOULD_BE_A_NUMBER": "値は数値でなければなりません。",
"THE_VALUE_SHOULD_BE_AMONG_THE_POSSIBLE_OPTIONS": "値は、以下の指定可能な値のいずれかでなければなりません。{0}。",
"UNRECOGNIZED_ELEMENT": "エレメント「{0}」は、このサーバーで認識されません。 その内容を編集するには、{1} に切り替えてください。",
"SERVER_NOT_FOUND": "ホストが「{1}」で、ユーザー・ディレクトリーが「{2}」であるサーバー「{0}」が見つかりませんでした。",
"FILE_NOT_FOUND_REPLACE": "ファイル「{0}」が見つかりませんでした。",
"ERROR_ACCESSING_SERVER_LIST": "集合内のサーバーのリストにアクセスできませんでした。",
"ERROR_NOT_IN_COLLECTIVE_ENVIRONMENT": "URL 中のリモート・サーバー参照は、集合環境の外側であるため、無効です。 ローカル・サーバー上の構成ファイルにアクセスするには、{0} をクリックしてください。",
"ERROR_ACCESSING_INCLUDE_FILES": "インクルード・ファイルにアクセスできませんでした。",
"PATH_NOT_AVAILABLE": "パスが使用不可です",
"HERE": "ここをクリックします。",
"CHANGE_SERVER": "サーバーの変更",
"ERROR_RETRIEVING_SERVER_INFORMATION": "サーバー情報を取得できませんでした。",
"SELECT_SERVER": "サーバーの選択",
"SERVER_DESCRIPTION": "左側のツリーでエレメントを選択すると、そのエレメントの構成が表示されます。",
"SELECT_ELEMENT_TO_VIEW_DESCRIPTION": "エレメントを選択すると、そのエレメントの説明が表示されます。",
"SAVING": "保存しています...",
"SERVER_NAME": "サーバー名",
"CLUSTER": "クラスター",
"HOST": "ホスト",
"USER_DIRECTORY_PATH": "ユーザー・ディレクトリー・パス",
"SERVERS": "{0} サーバー",
"ONE_SERVER": "1 個のサーバー",
"SHOWING_FIRST_N_SERVERS": "(最初の 500 個のサーバーを表示しています)",
"COULD_NOT_RETRIEVE_SERVER_IDENTIFICATION": "サーバー識別情報を取得できませんでした。",
"CONTENT_ASSIST_AVAILABLE": "コンテンツ・アシストを使用するには Ctrl + スペースを押してください。",
"OPEN_FILE": "ファイルを開く",
"CREATE_FILE": "ファイルの作成",
"FILE_NOT_FOUND": "ファイルが見つかりません",
"CANNOT_ACCESS_FILE": "ファイルにアクセスできません",
"CREATING_FILE": "ファイルを作成しています...",
"SUCCESSFULLY_CREATED_FILE": "ファイルが正常に作成されました",
"COULD_NOT_CREATE_FILE": "ファイルを作成できませんでした",
"FILE_CHANGED_DURING_EDITING_DIALOG_TITLE": "保存の競合",
"OVERWRITE": "上書き",
"FILE_CHANGED_DURING_EDITING_DIALOG_MESSAGE": "ファイル {0} は、開かれた後に、別のユーザーまたはプロセスによって変更されました。",
"OVERWRITING": "上書きしています...",
"SEARCH": "検索",
"SETTINGS": "設定",
"LOCATION": "ロケーション",
"EXPAND": "展開",
"COLLAPSE": "省略表示",
"CLEAR": "クリア",
"EXPAND_COLLAPSE": "展開/省略",
"ELEMENT_INFORMATION_FORM": "エレメント情報フォーム",
"SOURCE_EDITOR": "ソース・エディター",
"SOURCE_EDITOR_CONTENT": "ソース・エディター・コンテンツ",
"SOURCE_EDITOR_MENU" : "メニュー・コンテンツ",
"ELEMENT_DESCRIPTION": "エレメントの説明",
"ELEMENT_SEARCH": "エレメント検索",
"ADD_CHILD_ELEMENT_DIALOG": "「子エレメントの追加」ダイアログ",
"REMOVE_ELEMENT_DIALOG": "「エレメントの削除」ダイアログ",
"VALIDATE_DATASOURCE_DIALOG": "「データ・ソース接続の検証」ダイアログ",
"VALIDATE_DATASOURCE": "データ・ソース接続のテスト",
"VALIDATE_CONNECTION_DIALOG":"接続ダイアログを検証する",
"DEFAULT_AUTHENTICATION_ALIAS":"デフォルト認証",
"SPECIFY_AUTHENTICATION_ALIAS":"認証別名の指定",
"LOGIN_CONFIG":"ログイン構成",
"LOGIN_CONFIG_ID":"構成IDをログインする",
"ADD_LOGIN_CONFIG_PROPERTY":"ログイン構成プロパティーの追加",
"KEY":"キー",
"CHOOSE_AN_OPTION":"オプションの選択",
"ENUMERATION_SELECTION_DIALOG": "「列挙選択」ダイアログ",
"SAVE_BEFORE_CLOSING_DIALOG": "「閉じる前に保存」ダイアログ",
"ERROR_SAVING_FILE_DIALOG": "「ファイル保存エラー」ダイアログ",
"FILE_CHANGED_DURING_EDITING_DIALOG": "「編集中にファイル変更」ダイアログ",
"SERVER_TABLE_CELL_FOR_SCREEN_READER": "クラスター {0}、ホスト {1}、ユーザー・ディレクトリー・パス {2}",
"WARNING": "警告",
"EMPTY_STRING_ATTRIBUTE_VALUE": "(空ストリング) 属性を削除するには「クリア」ボタンをクリックしてください",
"EMPTY_STRING_ELEMENT_VALUE": "(空ストリング)",
"NO_VALUE": "(値なし)",
"DEFAULTS": "デフォルト",
"PRIMARY": "1 次",
"AUTH_ALIAS_OPTIONAL": "認証別名 (オプション)",
"AUTH_ALIAS": "認証別名",
"CONTAINER_AUTHENTICATION": "コンテナー認証",
"RESOURCE_REFERENCE": "リソース参照",
"NO_RESOURCE_REFERENCE": "リソース参照がありません",
"RESOURCE_REFERENCE_WITH_APPILCATION_AUTHENTICATION_IN_USE": "アプリケーション認証で認証されるリソース参照を使用して、接続をテストします。",
"RESOURCE_REFERENCE_WITH_CONTAINER_AUTHENTICATION_IN_USE": "コンテナー認証で認証されるリソース参照を使用して、接続をテストします。",
"RESOURCE_REFERENCE_IN_USE": "直接検索のコンテナー認証が有効になっています。 接続テストは、構成されたデフォルトのコンテナー認証データを使用します。",
"NO_RESOURCE_REFERENCE_IN_USE": "リソース参照を使用せずに接続をテストします。",
"APPLICATION_AUTHENTICATION": "アプリケーション認証",
"CUSTOM_LOGIN_MODULE": "カスタム・ログイン・モジュール (オプション)",
"LOGIN_PROPERTIES": "ログイン・プロパティー (オプション)",
"TEST_RESULTS": "データ・ソースのテスト結果",
"OVERRIDES": "オーバーライド",
"SELECT_FEATURE_DIALOG": "「機能の選択」ダイアログ",
"SELECT_FEATURE": "機能の選択",
"FEATURE_DESCRIPTION": "機能の説明",
"SELECT_FEATURE_TO_VIEW_DESCRIPTION": "機能を選択すると、その機能の説明が表示されます。",
"SUPPORT_MESSAGE" : "サーバー構成は、Node.js サーバーに対しても、集合コントローラーを介してアクセスされる Docker コンテナー内のサーバーに対しても使用できません。",
"NO_ROLE_MESSAGE": "ユーザーは構成変更の権限を持つロールに属していません。 ファイルは読み取り専用モードで使用可能です。",
    
// Messages for Collective Debugging
"REQUIRED_ACTIONS" : "集合メンバーに必要なアクション:",
"RUN_UPDATE_HOST" : "rpcUser が書き込み権限を持つパスを '--hostWritePath' に指定して、'collective updateHost' または 'collective registerHost' を実行してください。  ホストの有効な資格情報も指定する必要があります。",
"REGISTERING_HOST_LINK" : "ホスト・コンピューターの Liberty 集合への登録",
"CONIFGURED_SSH_RXA" : "メンバー・サーバーの構成には、正しく構成された SSH または hostAuthInfo が必要です。",
"CONFIGURING_COLLECTIVE_LINK" : "Liberty 集合を構成しています",
"CONFIGURING_RXA_LINK" : "RXA を構成しています",
"TWO_LINKS" : "{0} および {1}", //Hyperlink 1 (CONFIGURING_COLLECTIVE_LINK) and hyperlink 2 (CONFIGURING_RXA_LINK)
"CONFIGRUED_READ_DIR" : "メンバーの構成では、サーバー構成を通じてアクセス可能な構成ファイルへの remoteFileAccess readDir アクセスを必ず指定してください。",
"DEFAULT_READ_DIR" : "注: デフォルトでは (および remoteFileAccess が構成に指定されていない場合)、以下の readDir が使用可能です。${wlp.install.dir}、${wlp.user.dir}、および ${server.output.dir}。  デフォルトで使用可能な writeDir はありません。",
"PUBLISHED_READ_DIR" : "メンバー・サーバーは、稼働中または以前に始動済みで、その remoteFileAccess readDir を正常に公開済みでなければなりません。",
"JAVA_AVAILABLE" : "JAVA をパスに必ず設定してください。 JAVA を設定するには、以下の 3 つの方法があります。",
"HOST_JAVA_HOME" : "collective updateHost コマンドで --hostJAVAHome パラメーターを指定します。 例: ./collective updateHost --host=[hostName] --user=[user] --password=[password] --port=[httpsPort] --hostJAVAHome=[pathToJAVA]",
"LINK_JAVA" : "/usr/bin に JAVA へのリンクを作成します。 例: /usr/bin/java -> [pathToJAVA]",
"JAVA_ON_PATH" : "PATH 環境変数に JAVA を追加します。  Linux の .bashrc で PATH のエクスポートを行います。", 
"MORE_INFORMATION" : "詳細については、以下を参照してください。 "
};
