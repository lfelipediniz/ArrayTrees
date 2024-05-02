#!/bin/bash

# Compila todos os arquivos Java na raiz do projeto
javac *.java

# Contadores para testes que passaram e falharam
passed=0
failed=0

# Array para armazenar nomes dos arquivos de testes que falharam
declare -a failed_tests

# Encontrar todos os arquivos de entrada e iterar sobre eles
for input_file in test/*.in; do
    # Extrair o número do teste do nome do arquivo
    test_number=$(echo $input_file | sed 's/test\/\([0-9]*\)\.in/\1/')

    # Verificar se o arquivo de saída correspondente existe
    output_file="test/$test_number.out"
    if [ ! -f $output_file ]; then
        echo "Output file for test $test_number not found."
        continue
    fi

    # Executar o programa Java com o arquivo de entrada e salvar a saída em um arquivo temporário
    # Timeout de 10 segundos usando o comando 'timeout'
    start_time=$(date +%s)
    if timeout 10s java Main < $input_file > test/temp_out.txt; then
        end_time=$(date +%s)
        elapsed_time=$(( end_time - start_time ))

        if [ $elapsed_time -ge 10 ]; then
            echo "Test $test_number failed (timeout)"
            ((failed++))
            failed_tests+=($input_file)
        else
            # Comparar a saída do programa com a saída esperada
            if diff -w -B -Z test/temp_out.txt $output_file > /dev/null; then
                echo "Test $test_number passed"
                ((passed++))
            else
                echo "Test $test_number failed"
                ((failed++))
                failed_tests+=($input_file)
                echo "Failed Test $test_number:"
                echo "Output Produced:"
                cat test/temp_out.txt
                echo "Expected Output:"
                cat $output_file
                echo "-------------------------"
            fi
        fi
    else
        # Se o programa não terminar antes do timeout ou se o comando 'timeout' falhar
        echo "Test $test_number failed (timeout error)"
        ((failed++))
        failed_tests+=($input_file)
    fi
done

# Remover o arquivo de saída temporário
rm test/temp_out.txt

# Exibir o número de testes que passaram e falharam
echo "$passed tests passed"
echo "$failed tests failed"

# Listar os nomes dos arquivos para os testes que falharam
if [ $failed -ne 0 ]; then
    echo "Failed test files:"
    for file in "${failed_tests[@]}"; do
        echo $file
    done
fi
